package pers.yan.video.admin.pojo.entity;



import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    private Integer id;

    private String name;

    private Integer sex;


    private String homePlace;


    private Date birthday;


    private String occupation;


    private String alias;

    private String imdbId;


    private boolean lockTag;


    private boolean deleteTag;


    private String portrait;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHomePlace() {
        return homePlace;
    }

    public void setHomePlace(String homePlace) {
        this.homePlace = homePlace;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public boolean isLockTag() {
        return lockTag;
    }

    public void setLockTag(boolean lockTag) {
        this.lockTag = lockTag;
    }

    public boolean isDeleteTag() {
        return deleteTag;
    }

    public void setDeleteTag(boolean deleteTag) {
        this.deleteTag = deleteTag;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", sex=").append(sex);
        sb.append(", homePlace=").append(homePlace);
        sb.append(", birthday=").append(birthday);
        sb.append(", occupation=").append(occupation);
        sb.append(", alias=").append(alias);
        sb.append(", imdbId=").append(imdbId);
        sb.append(", lockTag=").append(lockTag);
        sb.append(", deleteTag=").append(deleteTag);
        sb.append(", portrait=").append(portrait);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Person other = (Person) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getHomePlace() == null ? other.getHomePlace() == null : this.getHomePlace().equals(other.getHomePlace()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getOccupation() == null ? other.getOccupation() == null : this.getOccupation().equals(other.getOccupation()))
            && (this.getAlias() == null ? other.getAlias() == null : this.getAlias().equals(other.getAlias()))
            && (this.getImdbId() == null ? other.getImdbId() == null : this.getImdbId().equals(other.getImdbId()))
            && (this.isLockTag() == other.isLockTag())
            && (this.isDeleteTag() == other.isDeleteTag())
            && (this.getPortrait() == null ? other.getPortrait() == null : this.getPortrait().equals(other.getPortrait()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getHomePlace() == null) ? 0 : getHomePlace().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getOccupation() == null) ? 0 : getOccupation().hashCode());
        result = prime * result + ((getAlias() == null) ? 0 : getAlias().hashCode());
        result = prime * result + ((getImdbId() == null) ? 0 : getImdbId().hashCode());
        result = prime * result + (isLockTag() ? 1231 : 1237);
        result = prime * result + (isDeleteTag() ? 1231 : 1237);
        result = prime * result + ((getPortrait() == null) ? 0 : getPortrait().hashCode());
        return result;
    }
}