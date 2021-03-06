package no.nsd.qddt.domain.embedded;

import javax.persistence.Embeddable;

/**
 * @author Stig Norland
 */
@Embeddable
public class Version implements Comparable<Version> {

    private static final String versionFormat = "%1$s.%2$s %3$s";
    private Integer major=1;
    private Integer minor=0;
    private String versionLabel="";

//    @org.springframework.data.annotation.Version
//    private Long build;

    public Integer getMajor() {
        return major;
    }

    public void incMajor() {
        this.major++;
        this.minor =0;
    }

    public Integer getMinor() {
        return minor;
    }

    public void incMinor() {
        this.minor++;
    }

    public String getVersionLabel() {
        return versionLabel;
    }

    public void setVersionLabel(String versionLabel) {
        this.versionLabel = versionLabel;
    }

//    public Long getBuild() {
//        return build;
//    }
//
//    public void setBuild(Long build) {
//        this.build = build;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Version)) return false;

        Version version = (Version) o;

        if (major != null ? !major.equals(version.major) : version.major != null) return false;
        if (minor != null ? !minor.equals(version.minor) : version.minor != null) return false;
        return versionLabel != null ? versionLabel.equals(version.versionLabel) : version.versionLabel == null;

    }

    @Override
    public int hashCode() {
        int result = major != null ? major.hashCode() : 0;
        result = 31 * result + (minor != null ? minor.hashCode() : 0);
        result = 31 * result + (versionLabel != null ? versionLabel.hashCode() : 0);
        return result;
    }

    @Override
    public String
    toString() {
        return  String.format(versionFormat, major, minor, versionLabel);
    }

    @Override
    public int compareTo(Version o) {

        return this.getMajor().compareTo(o.getMajor()) + this.getMinor().compareTo(o.getMinor());

    }
}
