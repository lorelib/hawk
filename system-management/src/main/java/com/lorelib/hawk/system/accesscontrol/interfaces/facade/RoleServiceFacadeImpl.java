package com.lorelib.hawk.system.accesscontrol.interfaces.facade;

import com.lorelib.hawk.system.accesscontrol.application.RoleService;
import com.lorelib.hawk.system.accesscontrol.domain.Resource;
import com.lorelib.hawk.system.accesscontrol.domain.Role;
import com.lorelib.hawk.system.accesscontrol.interfaces.facade.assembler.RoleDTOAssembler;
import com.lorelib.hawk.system.accesscontrol.interfaces.facade.command.CreateRoleCommand;
import com.lorelib.hawk.system.accesscontrol.interfaces.facade.command.CreateRoleWithPermCommand;
import com.lorelib.hawk.system.accesscontrol.interfaces.facade.command.DeleteRoleCommand;
import com.lorelib.hawk.system.accesscontrol.interfaces.facade.command.UpdateRoleCommand;
import com.lorelib.hawk.system.accesscontrol.interfaces.facade.dto.RoleDTO;
import com.lorelib.hawk.infrastructure.helpers.mapper.BeanMapper;
import com.lorelib.hawk.infrastructure.helpers.utils.ParamsValidatorUtil;
import com.lorelib.hawk.infrastructure.stereotype.Facade;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author listening
 * @description RoleServiceFacadeImpl:
 * @create 2017 04 12 16:56.
 */
@Facade
public class RoleServiceFacadeImpl implements RoleServiceFacade {
    @Override
    public void addRole(CreateRoleCommand command) {
        ParamsValidatorUtil.validate(command);
        roleService.addRole(command.getRoleName(), command.getDescription());
    }

    @Override
    public void addRoleWithPerms(CreateRoleWithPermCommand command) {
        ParamsValidatorUtil.validate(command);
        roleService.addRoleWithPerms(command.getRoleName(), command.getDescription(),
                BeanMapper.mapList(command.getResources(), Resource.class));
    }

    @Override
    public List<RoleDTO> getAllRoleWithPerms() {
        List<Role> roles = roleService.getAllRoleWithPerms();
        return RoleDTOAssembler.toDTO(roles);
    }

    @Override
    public void updateRole(UpdateRoleCommand command) {
        ParamsValidatorUtil.validate(command);
        roleService.updateRole(command.getRoleId(), command.getRoleDesc(),
                BeanMapper.mapList(command.getResources(), Resource.class));
    }

    @Override
    public void deleteRole(DeleteRoleCommand command) {
        ParamsValidatorUtil.validate(command);
        roleService.deleteRole(command.getRoleId());
    }

    @Autowired
    private RoleService roleService;
}