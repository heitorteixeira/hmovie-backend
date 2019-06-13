package br.com.hmovie.dto.response;

import java.io.Serializable;

public class MovieDatesDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String maximum;
	private String minimum;
	
	public String getMaximum() {
		return maximum;
	}
	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}
	public String getMinimum() {
		return minimum;
	}
	public void setMinimum(String minimum) {
		this.minimum = minimum;
	}

	
}
