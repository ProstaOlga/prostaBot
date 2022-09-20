/*
 * This file is generated by jOOQ.
 */
package jooq.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FlywaySchemaHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer       installedRank;
    private String        version;
    private String        description;
    private String        type;
    private String        script;
    private Integer       checksum;
    private String        installedBy;
    private LocalDateTime installedOn;
    private Integer       executionTime;
    private Boolean       success;

    public FlywaySchemaHistory() {}

    public FlywaySchemaHistory(FlywaySchemaHistory value) {
        this.installedRank = value.installedRank;
        this.version = value.version;
        this.description = value.description;
        this.type = value.type;
        this.script = value.script;
        this.checksum = value.checksum;
        this.installedBy = value.installedBy;
        this.installedOn = value.installedOn;
        this.executionTime = value.executionTime;
        this.success = value.success;
    }

    public FlywaySchemaHistory(
        Integer       installedRank,
        String        version,
        String        description,
        String        type,
        String        script,
        Integer       checksum,
        String        installedBy,
        LocalDateTime installedOn,
        Integer       executionTime,
        Boolean       success
    ) {
        this.installedRank = installedRank;
        this.version = version;
        this.description = description;
        this.type = type;
        this.script = script;
        this.checksum = checksum;
        this.installedBy = installedBy;
        this.installedOn = installedOn;
        this.executionTime = executionTime;
        this.success = success;
    }

    /**
     * Getter for <code>public.flyway_schema_history.installed_rank</code>.
     */
    public Integer getInstalledRank() {
        return this.installedRank;
    }

    /**
     * Setter for <code>public.flyway_schema_history.installed_rank</code>.
     */
    public void setInstalledRank(Integer installedRank) {
        this.installedRank = installedRank;
    }

    /**
     * Getter for <code>public.flyway_schema_history.version</code>.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Setter for <code>public.flyway_schema_history.version</code>.
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Getter for <code>public.flyway_schema_history.description</code>.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>public.flyway_schema_history.description</code>.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for <code>public.flyway_schema_history.type</code>.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Setter for <code>public.flyway_schema_history.type</code>.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for <code>public.flyway_schema_history.script</code>.
     */
    public String getScript() {
        return this.script;
    }

    /**
     * Setter for <code>public.flyway_schema_history.script</code>.
     */
    public void setScript(String script) {
        this.script = script;
    }

    /**
     * Getter for <code>public.flyway_schema_history.checksum</code>.
     */
    public Integer getChecksum() {
        return this.checksum;
    }

    /**
     * Setter for <code>public.flyway_schema_history.checksum</code>.
     */
    public void setChecksum(Integer checksum) {
        this.checksum = checksum;
    }

    /**
     * Getter for <code>public.flyway_schema_history.installed_by</code>.
     */
    public String getInstalledBy() {
        return this.installedBy;
    }

    /**
     * Setter for <code>public.flyway_schema_history.installed_by</code>.
     */
    public void setInstalledBy(String installedBy) {
        this.installedBy = installedBy;
    }

    /**
     * Getter for <code>public.flyway_schema_history.installed_on</code>.
     */
    public LocalDateTime getInstalledOn() {
        return this.installedOn;
    }

    /**
     * Setter for <code>public.flyway_schema_history.installed_on</code>.
     */
    public void setInstalledOn(LocalDateTime installedOn) {
        this.installedOn = installedOn;
    }

    /**
     * Getter for <code>public.flyway_schema_history.execution_time</code>.
     */
    public Integer getExecutionTime() {
        return this.executionTime;
    }

    /**
     * Setter for <code>public.flyway_schema_history.execution_time</code>.
     */
    public void setExecutionTime(Integer executionTime) {
        this.executionTime = executionTime;
    }

    /**
     * Getter for <code>public.flyway_schema_history.success</code>.
     */
    public Boolean getSuccess() {
        return this.success;
    }

    /**
     * Setter for <code>public.flyway_schema_history.success</code>.
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FlywaySchemaHistory (");

        sb.append(installedRank);
        sb.append(", ").append(version);
        sb.append(", ").append(description);
        sb.append(", ").append(type);
        sb.append(", ").append(script);
        sb.append(", ").append(checksum);
        sb.append(", ").append(installedBy);
        sb.append(", ").append(installedOn);
        sb.append(", ").append(executionTime);
        sb.append(", ").append(success);

        sb.append(")");
        return sb.toString();
    }
}
