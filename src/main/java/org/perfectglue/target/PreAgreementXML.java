package org.perfectglue.target;
//maybe at some point this gets fixed?
public class PreAgreementXML {
	public PreAgreementXML() {}
	public PreAgreementXML(Envelope envelope) {
		this.EnvelopeObject = envelope;
	}
	Envelope EnvelopeObject;

	// Getter Methods

	public Envelope getEnvelope() {
		return EnvelopeObject;
	}

	// Setter Methods

	public void setEnvelope(Envelope EnvelopeObject) {
		this.EnvelopeObject = EnvelopeObject;
	}
}

class Envelope {
	Body BodyObject;
	private String _xmlnssoapenv;
	private String __prefix;

	// Getter Methods

	public Body getBody() {
		return BodyObject;
	}

	public String get_xmlnssoapenv() {
		  return _xmlnssoapenv;
		 }

	public String get__prefix() {
		return __prefix;
	}

	// Setter Methods

	public void setBody(Body BodyObject) {
		this.BodyObject = BodyObject;
	}

	public void set_xmlnssoapenv(String _xmlnssoapenv) {
		  this._xmlnssoapenv = _xmlnssoapenv;
		 }

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}
}

class Body {
	SendenVAOStornoTVSAntwort SendenVAOStornoTVSAntwortObject;
	private String __prefix;

	// Getter Methods

	public SendenVAOStornoTVSAntwort getSendenVAOStornoTVSAntwort() {
		  return SendenVAOStornoTVSAntwortObject;
		 }

	public String get__prefix() {
		return __prefix;
	}

	// Setter Methods

	public void setSendenVAOStornoTVSAntwort(SendenVAOStornoTVSAntwort sendenVAOStornoTVSAntwortObject) {
		  this.SendenVAOStornoTVSAntwortObject = sendenVAOStornoTVSAntwortObject;
		 }

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}
}

class SendenVAOStornoTVSAntwort
{
	EKP EKPObject;
	Vorabstimmung VorabstimmungObject;
	Faxdatenblock FaxdatenblockObject;
	private String _xmlnsnaw;
	private String _xmlnstns;
	private String __prefix;

	// Getter Methods

	public EKP getEKP() {
		  return EKPObject;
		 }

	public Vorabstimmung getVorabstimmung() {
		return VorabstimmungObject;
	}

	public Faxdatenblock getFaxdatenblock() {
		return FaxdatenblockObject;
	}

	public String get_xmlnsnaw() {
		  return _xmlnsnaw;
		 }

	public String get_xmlnstns() {


	
		  return _xmlnstns;
		 }

	public String get__prefix() {
		return __prefix;
	}

	// Setter Methods

	public void setEKP(EKP EKPObject) {
		this.EKPObject = EKPObject;
	}

	public void setVorabstimmung(Vorabstimmung VorabstimmungObject) {
		this.VorabstimmungObject = VorabstimmungObject;
	}

	public void setFaxdatenblock(Faxdatenblock FaxdatenblockObject) {
		this.FaxdatenblockObject = FaxdatenblockObject;
	}

	public void set_xmlnsnaw(String _xmlnsnaw) {
		  this._xmlnsnaw = _xmlnsnaw;
		 }

	public void set_xmlnstns(String _xmlnstns) {
		  this._xmlnstns = _xmlnstns;
		 }

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}
}

class Faxdatenblock {
	Endkunde EndkundeObject;
	Geschaeftsfall GeschaeftsfallObject;
	private String __prefix;

	// Getter Methods

	public Endkunde getEndkunde() {
		return EndkundeObject;
	}

	public Geschaeftsfall getGeschaeftsfall() {
		return GeschaeftsfallObject;
	}

	public String get__prefix() {
		return __prefix;
	}

	// Setter Methods

	public void setEndkunde(Endkunde EndkundeObject) {
		this.EndkundeObject = EndkundeObject;
	}

	public void setGeschaeftsfall(Geschaeftsfall GeschaeftsfallObject) {
		this.GeschaeftsfallObject = GeschaeftsfallObject;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}
}

class Geschaeftsfall {
	private String __prefix;
	private String __text;

	// Getter Methods

	public String get__prefix() {
		return __prefix;
	}

	public String get__text() {
		return __text;
	}

	// Setter Methods

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}

	public void set__text(String __text) {
		this.__text = __text;
	}
}

class Endkunde {
	Person PersonObject;
	private String __prefix;

	// Getter Methods

	public Person getPerson() {
		return PersonObject;
	}

	public String get__prefix() {
		return __prefix;
	}

	// Setter Methods

	public void setPerson(Person personObject) {
		this.PersonObject = personObject;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}
}

class Person {
	Anrede AnredeObject;
	Nachname NachnameObject;
	private String __prefix;

	// Getter Methods

	public Anrede getAnrede() {
		return AnredeObject;
	}

	public Nachname getNachname() {
		return NachnameObject;
	}

	public String get__prefix() {
		return __prefix;
	}

	// Setter Methods

	public void setAnrede(Anrede anredeObject) {
		this.AnredeObject = anredeObject;
	}

	public void setNachname(Nachname nachnameObject) {
		this.NachnameObject = nachnameObject;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}
}

class Nachname {
	private String __prefix;
	private String __text;

	// Getter Methods

	public String get__prefix() {
		return __prefix;
	}

	public String get__text() {
		return __text;
	}

	// Setter Methods

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}

	public void set__text(String __text) {
		this.__text = __text;
	}
}

class Anrede {
	private String __prefix;
	private String __text;

	// Getter Methods

	public String get__prefix() {
		return __prefix;
	}

	public String get__text() {
		return __text;
	}

	// Setter Methods

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}

	public void set__text(String __text) {
		this.__text = __text;
	}
}

class Vorabstimmung {
	VorabstimmungsID VorabstimmungsIDObject;
	Geschaeftsfall GeschaeftsfallObject;
	ZustimmungWechsel ZustimmungWechselObject;
	private String __prefix;

	// Getter Methods

	public VorabstimmungsID getVorabstimmungsID() {
		return VorabstimmungsIDObject;
	}

	public Geschaeftsfall getGeschaeftsfall() {
		return GeschaeftsfallObject;
	}

	public ZustimmungWechsel getZustimmungWechsel() {
		return ZustimmungWechselObject;
	}

	public String get__prefix() {
		return __prefix;
	}

	// Setter Methods

	public void setVorabstimmungsID(VorabstimmungsID VorabstimmungsIDObject) {
		this.VorabstimmungsIDObject = VorabstimmungsIDObject;
	}

	public void setGeschaeftsfall(Geschaeftsfall GeschaeftsfallObject) {
		this.GeschaeftsfallObject = GeschaeftsfallObject;
	}

	public void setZustimmungWechsel(ZustimmungWechsel ZustimmungWechselObject) {
		this.ZustimmungWechselObject = ZustimmungWechselObject;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}
}

class ZustimmungWechsel {
	Meldungen MeldungenObject;
	Wechseltermin WechselterminObject;
	private String __prefix;

	// Getter Methods

	public Meldungen getMeldungen() {
		return MeldungenObject;
	}

	public Wechseltermin getWechseltermin() {
		return WechselterminObject;
	}

	public String get__prefix() {
		return __prefix;
	}

	// Setter Methods

	public void setMeldungen(Meldungen MeldungenObject) {
		this.MeldungenObject = MeldungenObject;
	}

	public void setWechseltermin(Wechseltermin WechselterminObject) {
		this.WechselterminObject = WechselterminObject;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}
}

class Wechseltermin {
	private String __prefix;
	private String __text;

	// Getter Methods

	public String get__prefix() {
		return __prefix;
	}

	public String get__text() {
		return __text;
	}

	// Setter Methods

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}

	public void set__text(String __text) {
		this.__text = __text;
	}
}

class Meldungen {
	Meldecode MeldecodeObject;
	private String __prefix;

	// Getter Methods

	public Meldecode getMeldecode() {
		return MeldecodeObject;
	}

	public String get__prefix() {
		return __prefix;
	}

	// Setter Methods

	public void setMeldecode(Meldecode MeldecodeObject) {
		this.MeldecodeObject = MeldecodeObject;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}
}

class Meldecode {
	private String __prefix;
	private String __text;

	// Getter Methods

	public String get__prefix() {
		return __prefix;
	}

	public String get__text() {
		return __text;
	}

	// Setter Methods

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}

	public void set__text(String __text) {
		this.__text = __text;
	}
}

class VorabstimmungsID {
	private String __prefix;
	private String __text;

	// Getter Methods

	public String get__prefix() {
		return __prefix;
	}

	public String get__text() {
		return __text;
	}

	// Setter Methods

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}

	public void set__text(String __text) {
		this.__text = __text;
	}
}

class EKP {
	EKPab EKPabObject;
	EKPauf EKPaufObject;
	private String __prefix;

	// Getter Methods

	public EKPab getEKPab() {
		return EKPabObject;
	}

	public EKPauf getEKPauf() {
		return EKPaufObject;
	}

	public String get__prefix() {
		return __prefix;
	}

	// Setter Methods

	public void setEKPab(EKPab EKPabObject) {
		this.EKPabObject = EKPabObject;
	}

	public void setEKPauf(EKPauf EKPaufObject) {
		this.EKPaufObject = EKPaufObject;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}
}

class EKPauf {
	EKPname EKPnameObject;
	InternerCarrierCode InternerCarrierCodeObject;
	private String __prefix;

	// Getter Methods

	public EKPname getEKPname() {
		return EKPnameObject;
	}

	public InternerCarrierCode getInternerCarrierCode() {
		return InternerCarrierCodeObject;
	}

	public String get__prefix() {
		return __prefix;
	}

	// Setter Methods

	public void setEKPname(EKPname EKPnameObject) {
		this.EKPnameObject = EKPnameObject;
	}

	public void setInternerCarrierCode(InternerCarrierCode internerCarrierCodeObject) {
		this.InternerCarrierCodeObject = internerCarrierCodeObject;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}
}

class InternerCarrierCode {
	private String __prefix;
	private String __text;

	// Getter Methods

	public String get__prefix() {
		return __prefix;
	}

	public String get__text() {
		return __text;
	}

	// Setter Methods

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}

	public void set__text(String __text) {
		this.__text = __text;
	}
}

class EKPname {
	private String __prefix;
	private String __text;

	// Getter Methods

	public String get__prefix() {
		return __prefix;
	}

	public String get__text() {
		return __text;
	}

	// Setter Methods

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}

	public void set__text(String __text) {
		this.__text = __text;
	}
}

class EKPab {
	EKPname EKPnameObject;
	InternerCarrierCode InternerCarrierCodeObject;
	private String __prefix;

	// Getter Methods

	public EKPname getEKPname() {
		return EKPnameObject;
	}

	public InternerCarrierCode getInternerCarrierCode() {
		return InternerCarrierCodeObject;
	}

	public String get__prefix() {
		return __prefix;
	}

	// Setter Methods

	public void setEKPname(EKPname EKPnameObject) {
		this.EKPnameObject = EKPnameObject;
	}

	public void setInternerCarrierCode(InternerCarrierCode internerCarrierCodeObject) {
		this.InternerCarrierCodeObject = internerCarrierCodeObject;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}
}
