package com.evrythng.thng.resource.model.store;

import java.util.HashMap;
import java.util.Map;

import com.evrythng.commons.EnumUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the parts of an address, consistent with JSR 179-1
 * This format of addressing is deemed to have provisions for the majority of
 * global addresses.
 * Modelled from Location API for J2ME - javax.microedition.location.AddressInfo
 * 
 * @author colin
 * 
 */
public class AddressInfo {

	/**
	 * Represents two-letter country codes
	 * ISO 3166-1-alpha-2 code
	 * Last updated: 2013-02-06
	 * 
	 * @author colin
	 * 
	 */
	public enum CountryCode {

		AF("AF", "AFGHANISTAN"), AX("AX", "ÅLAND ISLANDS"), AL("AL", "ALBANIA"), DZ("DZ", "ALGERIA"), AS("AS", "AMERICAN SAMOA"), AD("AD", "ANDORRA"), AO("AO", "ANGOLA"), AI("AI", "ANGUILLA"), AQ(
				"AQ", "ANTARCTICA"), AG("AG", "ANTIGUA AND BARBUDA"), AR("AR", "ARGENTINA"), AM("AM", "ARMENIA"), AW("AW", "ARUBA"), AU("AU", "AUSTRALIA"), AT("AT", "AUSTRIA"), AZ("AZ", "AZERBAIJAN"), BS(
				"BS", "BAHAMAS"), BH("BH", "BAHRAIN"), BD("BD", "BANGLADESH"), BB("BB", "BARBADOS"), BY("BY", "BELARUS"), BE("BE", "BELGIUM"), BZ("BZ", "BELIZE"), BJ("BJ", "BENIN"), BM("BM",
				"BERMUDA"), BT("BT", "BHUTAN"), BO("BO", "BOLIVIA, PLURINATIONAL STATE OF"), BQ("BQ", "BONAIRE, SINT EUSTATIUS AND SABA"), BA("BA", "BOSNIA AND HERZEGOVINA"), BW("BW", "BOTSWANA"), BV(
				"BV", "BOUVET ISLAND"), BR("BR", "BRAZIL"), IO("IO", "BRITISH INDIAN OCEAN TERRITORY"), BN("BN", "BRUNEI DARUSSALAM"), BG("BG", "BULGARIA"), BF("BF", "BURKINA FASO"), BI("BI",
				"BURUNDI"), KH("KH", "CAMBODIA"), CM("CM", "CAMEROON"), CA("CA", "CANADA"), CV("CV", "CAPE VERDE"), KY("KY", "CAYMAN ISLANDS"), CF("CF", "CENTRAL AFRICAN REPUBLIC"), TD("TD", "CHAD"), CL(
				"CL", "CHILE"), CN("CN", "CHINA"), CX("CX", "CHRISTMAS ISLAND"), CC("CC", "COCOS (KEELING) ISLANDS"), CO("CO", "COLOMBIA"), KM("KM", "COMOROS"), CG("CG", "CONGO"), CD("CD",
				"CONGO, THE DEMOCRATIC REPUBLIC OF THE"), CK("CK", "COOK ISLANDS"), CR("CR", "COSTA RICA"), CI("CI", "CÔTE D'IVOIRE"), HR("HR", "CROATIA"), CU("CU", "CUBA"), CW("CW", "CURAÇAO"), CY(
				"CY", "CYPRUS"), CZ("CZ", "CZECH REPUBLIC"), DK("DK", "DENMARK"), DJ("DJ", "DJIBOUTI"), DM("DM", "DOMINICA"), DO("DO", "DOMINICAN REPUBLIC"), EC("EC", "ECUADOR"), EG("EG", "EGYPT"), SV(
				"SV", "EL SALVADOR"), GQ("GQ", "EQUATORIAL GUINEA"), ER("ER", "ERITREA"), EE("EE", "ESTONIA"), ET("ET", "ETHIOPIA"), FK("FK", "FALKLAND ISLANDS (MALVINAS)"), FO("FO", "FAROE ISLANDS"), FJ(
				"FJ", "FIJI"), FI("FI", "FINLAND"), FR("FR", "FRANCE"), GF("GF", "FRENCH GUIANA"), PF("PF", "FRENCH POLYNESIA"), TF("TF", "FRENCH SOUTHERN TERRITORIES"), GA("GA", "GABON"), GM("GM",
				"GAMBIA"), GE("GE", "GEORGIA"), DE("DE", "GERMANY"), GH("GH", "GHANA"), GI("GI", "GIBRALTAR"), GR("GR", "GREECE"), GL("GL", "GREENLAND"), GD("GD", "GRENADA"), GP("GP", "GUADELOUPE"), GU(
				"GU", "GUAM"), GT("GT", "GUATEMALA"), GG("GG", "GUERNSEY"), GN("GN", "GUINEA"), GW("GW", "GUINEA-BISSAU"), GY("GY", "GUYANA"), HT("HT", "HAITI"), HM("HM",
				"HEARD ISLAND AND MCDONALD ISLANDS"), VA("VA", "HOLY SEE (VATICAN CITY STATE)"), HN("HN", "HONDURAS"), HK("HK", "HONG KONG"), HU("HU", "HUNGARY"), IS("IS", "ICELAND"), IN("IN",
				"INDIA"), ID("ID", "INDONESIA"), IR("IR", "IRAN, ISLAMIC REPUBLIC OF"), IQ("IQ", "IRAQ"), IE("IE", "IRELAND"), IM("IM", "ISLE OF MAN"), IL("IL", "ISRAEL"), IT("IT", "ITALY"), JM("JM",
				"JAMAICA"), JP("JP", "JAPAN"), JE("JE", "JERSEY"), JO("JO", "JORDAN"), KZ("KZ", "KAZAKHSTAN"), KE("KE", "KENYA"), KI("KI", "KIRIBATI"), KP("KP",
				"KOREA, DEMOCRATIC PEOPLE'S REPUBLIC OF"), KR("KR", "KOREA, REPUBLIC OF"), KW("KW", "KUWAIT"), KG("KG", "KYRGYZSTAN"), LA("LA", "LAO PEOPLE'S DEMOCRATIC REPUBLIC"), LV("LV", "LATVIA"), LB(
				"LB", "LEBANON"), LS("LS", "LESOTHO"), LR("LR", "LIBERIA"), LY("LY", "LIBYA"), LI("LI", "LIECHTENSTEIN"), LT("LT", "LITHUANIA"), LU("LU", "LUXEMBOURG"), MO("MO", "MACAO"), MK("MK",
				"MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF"), MG("MG", "MADAGASCAR"), MW("MW", "MALAWI"), MY("MY", "MALAYSIA"), MV("MV", "MALDIVES"), ML("ML", "MALI"), MT("MT", "MALTA"), MH("MH",
				"MARSHALL ISLANDS"), MQ("MQ", "MARTINIQUE"), MR("MR", "MAURITANIA"), MU("MU", "MAURITIUS"), YT("YT", "MAYOTTE"), MX("MX", "MEXICO"), FM("FM", "MICRONESIA, FEDERATED STATES OF"), MD(
				"MD", "MOLDOVA, REPUBLIC OF"), MC("MC", "MONACO"), MN("MN", "MONGOLIA"), ME("ME", "MONTENEGRO"), MS("MS", "MONTSERRAT"), MA("MA", "MOROCCO"), MZ("MZ", "MOZAMBIQUE"), MM("MM",
				"MYANMAR"), NA("NA", "NAMIBIA"), NR("NR", "NAURU"), NP("NP", "NEPAL"), NL("NL", "NETHERLANDS"), NC("NC", "NEW CALEDONIA"), NZ("NZ", "NEW ZEALAND"), NI("NI", "NICARAGUA"), NE("NE",
				"NIGER"), NG("NG", "NIGERIA"), NU("NU", "NIUE"), NF("NF", "NORFOLK ISLAND"), MP("MP", "NORTHERN MARIANA ISLANDS"), NO("NO", "NORWAY"), OM("OM", "OMAN"), PK("PK", "PAKISTAN"), PW("PW",
				"PALAU"), PS("PS", "PALESTINE, STATE OF"), PA("PA", "PANAMA"), PG("PG", "PAPUA NEW GUINEA"), PY("PY", "PARAGUAY"), PE("PE", "PERU"), PH("PH", "PHILIPPINES"), PN("PN", "PITCAIRN"), PL(
				"PL", "POLAND"), PT("PT", "PORTUGAL"), PR("PR", "PUERTO RICO"), QA("QA", "QATAR"), RE("RE", "RÉUNION"), RO("RO", "ROMANIA"), RU("RU", "RUSSIAN FEDERATION"), RW("RW", "RWANDA"), BL(
				"BL", "SAINT BARTHÉLEMY"), SH("SH", "SAINT HELENA, ASCENSION AND TRISTAN DA CUNHA"), KN("KN", "SAINT KITTS AND NEVIS"), LC("LC", "SAINT LUCIA"), MF("MF", "SAINT MARTIN (FRENCH PART)"), PM(
				"PM", "SAINT PIERRE AND MIQUELON"), VC("VC", "SAINT VINCENT AND THE GRENADINES"), WS("WS", "SAMOA"), SM("SM", "SAN MARINO"), ST("ST", "SAO TOME AND PRINCIPE"), SA("SA", "SAUDI ARABIA"), SN(
				"SN", "SENEGAL"), RS("RS", "SERBIA"), SC("SC", "SEYCHELLES"), SL("SL", "SIERRA LEONE"), SG("SG", "SINGAPORE"), SX("SX", "SINT MAARTEN (DUTCH PART)"), SK("SK", "SLOVAKIA"), SI("SI",
				"SLOVENIA"), SB("SB", "SOLOMON ISLANDS"), SO("SO", "SOMALIA"), ZA("ZA", "SOUTH AFRICA"), GS("GS", "SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS"), SS("SS", "SOUTH SUDAN"), ES("ES",
				"SPAIN"), LK("LK", "SRI LANKA"), SD("SD", "SUDAN"), SR("SR", "SURINAME"), SJ("SJ", "SVALBARD AND JAN MAYEN"), SZ("SZ", "SWAZILAND"), SE("SE", "SWEDEN"), CH("CH", "SWITZERLAND"), SY(
				"SY", "SYRIAN ARAB REPUBLIC"), TW("TW", "TAIWAN, PROVINCE OF CHINA"), TJ("TJ", "TAJIKISTAN"), TZ("TZ", "TANZANIA, UNITED REPUBLIC OF"), TH("TH", "THAILAND"), TL("TL", "TIMOR-LESTE"), TG(
				"TG", "TOGO"), TK("TK", "TOKELAU"), TO("TO", "TONGA"), TT("TT", "TRINIDAD AND TOBAGO"), TN("TN", "TUNISIA"), TR("TR", "TURKEY"), TM("TM", "TURKMENISTAN"), TC("TC",
				"TURKS AND CAICOS ISLANDS"), TV("TV", "TUVALU"), UG("UG", "UGANDA"), UA("UA", "UKRAINE"), AE("AE", "UNITED ARAB EMIRATES"), GB("GB", "UNITED KINGDOM"), US("US", "UNITED STATES"), UM(
				"UM", "UNITED STATES MINOR OUTLYING ISLANDS"), UY("UY", "URUGUAY"), UZ("UZ", "UZBEKISTAN"), VU("VU", "VANUATU"), VE("VE", "VENEZUELA, BOLIVARIAN REPUBLIC OF"), VN("VN", "VIET NAM"), VG(
				"VG", "VIRGIN ISLANDS, BRITISH"), VI("VI", "VIRGIN ISLANDS, U.S."), WF("WF", "WALLIS AND FUTUNA"), EH("EH", "WESTERN SAHARA"), YE("YE", "YEMEN"), ZM("ZM", "ZAMBIA"), ZW("ZW",
				"ZIMBABWE");

		private static Map<String, CountryCode> names = new HashMap<String, CountryCode>();
		private static Map<String, String> countryNames;
		private String code;
		private String country;

		private CountryCode(String code, String country) {
			this.code = code;
			this.country = country;
			addCountry(country, code);
		}

		static {
			names = EnumUtils.createNames(values());
		}

		private static void addCountry(String country, String code) {
			if (countryNames == null) {
				countryNames = new HashMap<String, String>();
			}
			countryNames.put(country, code);
		}

		public String getCountry() {
			return country;
		}

		@JsonValue
		@Override
		public String toString() {
			return code;
		}

		@JsonCreator
		public static CountryCode fromString(String name) {
			return EnumUtils.fromString(names, name == null ? name : name.toUpperCase());
		}

		public static String getCodeFromCountryName(String name) {
			try {
				if (!countryNames.containsKey(name.trim().toUpperCase())) {
					throw new NullPointerException();
				}
				return countryNames.get(name.trim().toUpperCase());
			} catch (Exception e) {
				throw new IllegalArgumentException("Country name not recognised", e);
			}
		}
	}

	private String extension;

	private String street;

	private String postalCode;

	private String city;

	private String county;

	private String state;

	private String country;

	private CountryCode countryCode;

	private String district;

	private String buildingName;

	private String buildingFloor;

	private String buildingRoom;

	private String buildingZone;

	private String crossing1;

	private String crossing2;

	public AddressInfo() {

	}

	public AddressInfo(String extension, String street, String postalCode, String city, String county, String state, String country, CountryCode countryCode, String district, String buildingName,
			String buildingFloor, String buildingRoom, String buildingZone, String crossing1, String crossing2) {
		this.extension = extension;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.county = county;
		this.state = state;
		this.country = country;
		this.countryCode = countryCode;
		this.district = district;
		this.buildingName = buildingName;
		this.buildingFloor = buildingFloor;
		this.buildingRoom = buildingRoom;
		this.buildingZone = buildingZone;
		this.crossing1 = crossing1;
		this.crossing2 = crossing2;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public CountryCode getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingFloor() {
		return buildingFloor;
	}

	public void setBuildingFloor(String buildingFloor) {
		this.buildingFloor = buildingFloor;
	}

	public String getBuildingRoom() {
		return buildingRoom;
	}

	public void setBuildingRoom(String buildingRoom) {
		this.buildingRoom = buildingRoom;
	}

	public String getBuildingZone() {
		return buildingZone;
	}

	public void setBuildingZone(String buildingZone) {
		this.buildingZone = buildingZone;
	}

	public String getCrossing1() {
		return crossing1;
	}

	public void setCrossing1(String crossing1) {
		this.crossing1 = crossing1;
	}

	public String getCrossing2() {
		return crossing2;
	}

	public void setCrossing2(String crossing2) {
		this.crossing2 = crossing2;
	}
}
