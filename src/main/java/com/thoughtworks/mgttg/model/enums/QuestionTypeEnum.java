package com.thoughtworks.mgttg.model.enums;

/**
 * @author talipk
 *
 */
public enum QuestionTypeEnum {

	Q_CHARACTER_CALCULATION(1L),
	Q_MATERIAL_COST_CALCULATION(2L),
	Q_UNDEFINED(3L),
	Q_UNDEFINED_MATERIAL(4L);

	private Long id;

	private QuestionTypeEnum(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}
