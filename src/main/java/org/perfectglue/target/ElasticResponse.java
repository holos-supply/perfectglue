package org.perfectglue.target;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ElasticResponse {
Hits hits;

public ElasticResponse(Hits hits) {
	super();
	this.hits = hits;
}

public Hits getHits() {
	return hits;
}

public void setHits(Hits hits) {
	this.hits = hits;
}
}
class Hits {
	public Hits(RealHits[] hits) {
		super();
		this.hits = hits;
	}
	RealHits[] hits;
	public RealHits[] getHits() {
		return hits;
	}
	public void setHits(RealHits[] hits) {
		this.hits = hits;
	}
	class RealHits{
		public RealHits(Source _source) {
			super();
			this._source = _source;
		}
		Source _source;
		public Source get_source() {
			return _source;
		}
		public void set_source(Source _source) {
			this._source = _source;
		}
		class Source {
			public Source(String value, String businessKey) {
				super();
				this.value = value;
				this.businessKey = businessKey;
			}
			String value;
			String businessKey;
			public String getValue() {
				return value;
			}
			public void setValue(String value) {
				this.value = value;
			}
			public String getBusinessKey() {
				return businessKey;
			}
			public void setBusinessKey(String businessKey) {
				this.businessKey = businessKey;
			}
		}
	}
}