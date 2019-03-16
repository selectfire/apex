package com.selectfire.apex.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.selectfire.apex.model.History;
import com.selectfire.apex.model.Platform;
import com.selectfire.apex.model.User;
import com.selectfire.apex.model.UserTracker;
import com.selectfire.apex.model.apisender.ChildrenStateVo;
import com.selectfire.apex.model.apisender.ChildrenVo;
import com.selectfire.apex.model.apisender.DataVo;
import com.selectfire.apex.model.apisender.MetadataVo;
import com.selectfire.apex.model.apisender.ResponseDataVo;
import com.selectfire.apex.repository.HistoryRepository;
import com.selectfire.apex.repository.PlatformRepository;
import com.selectfire.apex.repository.UserRepository;
import com.selectfire.apex.repository.UserTrackerRepository;

@Service
public class TrackerService {

	private HistoryRepository historyRepository;
	private PlatformRepository platformRepository;
	private UserRepository userRepository;
	private UserTrackerRepository userTrackerRepository;

	@Autowired
	public TrackerService(HistoryRepository historyRepository, PlatformRepository platformRepository,
			UserRepository userRepository, UserTrackerRepository userTrackerRepository) {
		this.historyRepository = historyRepository;
		this.platformRepository = platformRepository;
		this.userRepository = userRepository;
		this.userTrackerRepository = userTrackerRepository;
	}

	public User get(String name, int platformId) {

		Platform platform = platformRepository.findById(platformId);
		int trackerPlatformId = platform.getTrackerId();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		headers.add("TRN-Api-Key", "4533a56a-2f8b-4b82-86bc-6a2c1e1caf87");

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		String url = String.format("https://public-api.tracker.gg/apex/v1/standard/profile/%d/%s", trackerPlatformId,
				name);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ResponseDataVo> responseObj = restTemplate.exchange(url, HttpMethod.GET, entity,
				ResponseDataVo.class);

		if (responseObj.getStatusCode() == HttpStatus.OK) {
			History history = saveHistory(name, platformId, responseObj);

			User user = saveUser(name, platform, responseObj);

			saveUserTrackers(user.getId(), history.getId(), responseObj);
		}

		return null;
	}

	private History saveHistory(String name, int platformId, ResponseEntity<ResponseDataVo> responseObj) {
		History history = new History();
		history.setUsername(name);
		history.setPlatformId(platformId);
		history.setCreatedAt(LocalDateTime.now());
		history.setResponse(responseObj.toString());

		return historyRepository.save(history);
	}

	private User saveUser(String name, Platform platform, ResponseEntity<ResponseDataVo> responseObj) {
		User user = userRepository.findByNameAndPlatformId(name, platform.getId());
		if (user == null) {
			user = new User();
		}

		MetadataVo metadataVo = responseObj.getBody().getData().getMetadata();
		user.setName(name);
		user.setPlatform(platform);
		user.setLevel(metadataVo.getLevel());
		user.setAccountId(metadataVo.getAccountId());

		return userRepository.save(user);
	}

	private void saveUserTrackers(int userId, int historyId, ResponseEntity<ResponseDataVo> responseObj) {
		List<UserTracker> userTrackers = new ArrayList<>();

		DataVo dataVo = responseObj.getBody().getData();

		for (ChildrenVo childrenVo : dataVo.getChildren()) {

			List<ChildrenStateVo> childrenStates = childrenVo.getStats();
			for (ChildrenStateVo childrenStateVo : childrenStates) {

				UserTracker userTracker = new UserTracker();
				userTracker.setUserId(userId);
				userTracker.setHistoryId(historyId);
				userTracker.setKey(childrenStateVo.getMetadata().getKey());
				userTracker.setCategoryKey(childrenStateVo.getMetadata().getCategoryKey());
				userTracker.setValue(childrenStateVo.getValue());
				userTracker.setPercentile(childrenStateVo.getPercentile());
				userTracker.setCreatedAt(LocalDateTime.now());

				int legendId = Integer.valueOf(childrenVo.getId().replaceAll("^legend_", ""));
				userTracker.setLegendId(legendId);

				userTrackers.add(userTracker);
			}
		}

		userTrackerRepository.saveAll(userTrackers);
	}
}
