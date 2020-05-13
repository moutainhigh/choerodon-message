package io.choerodon.message.api.vo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author jiameng.cao
 * @date 2019/11/4
 */
public class WebhookRecordVO {
    private Long recordId;
    @ApiModelProperty(value = "消息Code")
    private String messageCode;

    @ApiModelProperty(value = "消息名称")
    private String messageName;

    @ApiModelProperty(value = "webhook发送地址")
    private String webHookAddress;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "webhook类型")
    private String webHookType;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "错误信息")
    private String errorInfo;

    @ApiModelProperty(value = "执行时间")
    private Date creationDate;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getWebHookAddress() {
        return webHookAddress;
    }

    public void setWebHookAddress(String webHookAddress) {
        this.webHookAddress = webHookAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWebHookType() {
        return webHookType;
    }

    public void setWebHookType(String webHookType) {
        this.webHookType = webHookType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
