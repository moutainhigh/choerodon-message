package io.choerodon.message.app.task;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.choerodon.asgard.schedule.QuartzDefinition;
import io.choerodon.asgard.schedule.annotation.JobParam;
import io.choerodon.asgard.schedule.annotation.JobTask;
import io.choerodon.asgard.schedule.annotation.TimedTask;
import io.choerodon.message.app.service.MessageCheckLogService;
import io.choerodon.message.infra.mapper.MessageC7nMapper;

/**
 * Created by wangxiang on 2020/8/25
 */
@Component
public class HzeroMessageTask {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    /**
     * 清理消息类型 WEBHOOK/EMAIL
     */
    private final String MESSAGE_TYPE = "messageType";

    /**
     * 清理多少天前的数据
     */
    private final String CLEAN_NUM = "cleanNum";

    private MessageC7nMapper messageC7nMapper;

    private MessageCheckLogService messageCheckLogService;

    public HzeroMessageTask(MessageC7nMapper messageC7nMapper,
                            MessageCheckLogService messageCheckLogService) {
        this.messageC7nMapper = messageC7nMapper;
        this.messageCheckLogService = messageCheckLogService;
    }


    /**
     * 清除消息发送记录
     *
     * @param data
     * @return map
     */
    @JobTask(code = "cleanMessageRecord", maxRetryCount = 0, description = "清除消息发送记录",
            params = {
                    @JobParam(name = MESSAGE_TYPE, description = "清理消息类型"),
                    @JobParam(name = CLEAN_NUM, description = "清理多少天前的数据", type = Integer.class)})
    public Map<String, Object> cleanRecord(Map<String, Object> data) {
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>begin clearing records<<<<<<<<<<<<<<<<<<<<<<<<<<");
        try {
            messageC7nMapper.deleteRecord(String.valueOf(data.get(MESSAGE_TYPE)), Integer.valueOf(String.valueOf(data.get(CLEAN_NUM))));
        } catch (Exception e) {
            LOGGER.error("error.clean.records", e);
        }
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>end clearing records<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return data;
    }

    @JobTask(maxRetryCount = 3, code = "clearMessageTemplate", description = "清理废弃的模板数据")
    @TimedTask(name = "clearMessageTemplate", description = "清理废弃的模板数据", oneExecution = true,
            repeatCount = 0, repeatInterval = 1, repeatIntervalUnit = QuartzDefinition.SimpleRepeatIntervalUnit.HOURS, params = {})
    public void clearMessageTemplate(Map<String, Object> map) {
        LOGGER.info("begin to clear message template.");
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>begin to clear message template<<<<<<<<<<<<<<<<<<<<<<<<<<");
        try {
            messageCheckLogService.checkLog("0.24.0");
        } catch (Exception e) {
            LOGGER.error("error.clear.message.template", e);
        }
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>end clear message template<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }


    /**
     * 清理所有项目层设置的数据
     * @param map
     */
    @JobTask(maxRetryCount = 3, code = "clearProjectMessageSetting", description = "清理项目层的通知设置")
    @TimedTask(name = "clearProjectMessageSetting", description = "清理项目层的通知设置", oneExecution = true,
            repeatCount = 0, repeatInterval = 1, repeatIntervalUnit = QuartzDefinition.SimpleRepeatIntervalUnit.HOURS, params = {})
    public void clearProjectMessageSetting(Map<String, Object> map) {
        LOGGER.info("begin to clear project message setting.");
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>begin to clear project message setting<<<<<<<<<<<<<<<<<<<<<<<<<<");
        try {
            messageCheckLogService.checkLog("0.24.alpha");
        } catch (Exception e) {
            LOGGER.error("error.clear.project.message.setting", e);
        }
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>end clear message project message setting<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }


}
