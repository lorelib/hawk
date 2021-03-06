package com.lorelib.hawk.system.interfaces.facade.accesscontrol.dto;

import com.lorelib.hawk.system.infrastructure.persistence.ResourceType;
import com.lorelib.hawk.infrastructure.stereotype.Dto;

import javax.validation.constraints.NotNull;

/**
 * @author listening
 * @description ResourceDto:
 * @create 2017 04 18 11:17.
 */
public class ResourceDto implements Dto {
    /**
     * 资源标识
     */
    @NotNull(message = "资源ID是必需的")
    private Long resourceId;

    /**
     * 资源名称
     */
    @NotNull(message = "资源名称是必需的")
    private String resourceName;

    /**
     * 资源类型
     */
    @NotNull(message = "资源类型是必需的")
    private String resourceType;

    public ResourceDto() {
    }

    public ResourceDto(ResourceType resourceType, Long resourceId) {
        this.resourceType = resourceType.name();
        this.resourceId = resourceId;
    }

    public ResourceDto(ResourceType resourceType, Long resourceId, String resourceName) {
        this(resourceType, resourceId);
        this.resourceName = resourceName;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}
