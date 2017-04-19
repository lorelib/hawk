package com.lorelib.hawk.webadmin.web.controller;

import com.alibaba.fastjson.JSON;
import com.lorelib.hawk.system.accesscontrol.interfaces.facade.command.CreateRoleCommand;
import com.lorelib.hawk.system.accesscontrol.interfaces.facade.command.CreateRoleWithPermCommand;
import com.lorelib.hawk.system.accesscontrol.interfaces.facade.command.DeleteRoleCommand;
import com.lorelib.hawk.system.accesscontrol.interfaces.facade.command.UpdateRoleCommand;
import com.lorelib.hawk.system.accesscontrol.interfaces.facade.dto.ResourceDTO;
import com.lorelib.hawk.system.menu.ResourceType;
import com.lorelib.hawk.infrastructure.test.TestNGUtil;
import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * @author listening
 * @description RoleControllerTest:
 * @create 2017 04 13 16:22.
 */
public class RoleControllerTest extends TestNGUtil {
    @Test
    public void addRoleTest() throws Exception {
        CreateRoleCommand command = new CreateRoleCommand("普通职员2", "");
        MvcResult result = mockMvc.perform(
                post("/role/addRole")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(JSON.toJSONString(command))
        ).andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), 200);
        println("响应结果: " + result.getResponse().getContentAsString());
    }

    @Test
    public void addRoleWithPermsTest() throws Exception {
        ResourceDTO r1 = new ResourceDTO(ResourceType.MENU, 4L, "数据管理");
        ResourceDTO r2 = new ResourceDTO(ResourceType.MENU, 6L, "角色管理");
        List<ResourceDTO> resources = Lists.newArrayList(r1, r2);

        CreateRoleWithPermCommand command = new CreateRoleWithPermCommand("普通职员3", "", resources);
        MvcResult result = mockMvc.perform(
                post("/role/addRoleWithPerms")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(JSON.toJSONString(command))
        ).andReturn();
        println("request data: ", command);
        Assert.assertEquals(result.getResponse().getStatus(), 200);
        println("响应结果: " + result.getResponse().getContentAsString());
    }

    @Test
    public void getAllRoleWithPermsTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/role/getAllRoleWithPerms")).andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), 200);
        println("响应结果: " + result.getResponse().getContentAsString());
    }

    @Test
    public void updateRoleTest()  throws Exception {
        ResourceDTO r1 = new ResourceDTO(ResourceType.MENU, 4L, "数据管理");
        ResourceDTO r2 = new ResourceDTO(ResourceType.MENU, 6L, "角色管理");
        List<ResourceDTO> resources = Lists.newArrayList(r1, r2);

        UpdateRoleCommand command = new UpdateRoleCommand(854191890841698304L, "", resources);
        MvcResult result = mockMvc.perform(
                post("/role/updateRole")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(JSON.toJSONString(command))
        ).andReturn();
        println("request data: ", command);
        Assert.assertEquals(result.getResponse().getStatus(), 200);
        println("响应结果: " + result.getResponse().getContentAsString());
    }

    @Test
    public void deleteRoleTest()  throws Exception {
        DeleteRoleCommand command = new DeleteRoleCommand(854206185560793088L);
        MvcResult result = mockMvc.perform(
                post("/role/deleteRole")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(JSON.toJSONString(command))
        ).andReturn();
        println("request data: ", command);
        Assert.assertEquals(result.getResponse().getStatus(), 200);
        println("响应结果: " + result.getResponse().getContentAsString());
    }
}