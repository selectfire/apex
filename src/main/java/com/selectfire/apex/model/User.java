package com.selectfire.apex.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.http.ResponseEntity;

import com.selectfire.apex.model.apisender.ResponseDataVo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {

	public User(ResponseEntity<ResponseDataVo> entity) {
		ResponseDataVo responseDataVo = entity.getBody();

		name = responseDataVo.getData().getMetadata().getPlatformUserHandle();
		level = responseDataVo.getData().getMetadata().getLevel();

		List<UserTracker> userTrackerList = new ArrayList<>();

		for (int i = 0; i < responseDataVo.getData().getChildren().size(); i++) {

			UserTracker userTracker = new UserTracker();

			// 레전드 아이디
			String splitString = responseDataVo.getData().getChildren().get(i).getId();
			String[] splitedString = splitString.split("_");
//			userTracker.setLegendId(Integer.parseInt(splitedString[1]));
			// 사용자 아이디 넣고

			// 키
			for (int j = 0; j < responseDataVo.getData().getChildren().get(i).getStats().size(); j++) {

				responseDataVo.getData().getChildren().get(i).getStats().get(j).getMetadata().getKey();
				responseDataVo.getData().getChildren().get(i).getStats().get(j).getMetadata().getCategoryKey();
				responseDataVo.getData().getChildren().get(i).getStats().get(j).getValue();

				if (responseDataVo.getData().getChildren().get(i).getStats().get(j).getValue() != 0) {
					responseDataVo.getData().getChildren().get(i).getStats().get(j).getPercentile();
				}

				userTrackerList.add(userTracker);

			}

			//

			// 값

			// 퍼세트

		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@OneToOne
	@JoinColumn(name = "platformId")
	private Platform platform;

	private String accountId;

	private int level;

	@OneToMany
	@JoinColumn(name = "userId")
	private List<UserTracker> userTracker;
}
