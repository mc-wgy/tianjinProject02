package com.tianjin.frontLoaded.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianjin.frontLoaded.bean.model.sys.SysRole;
import com.tianjin.frontLoaded.bean.model.sys.SysUser;
import com.tianjin.frontLoaded.mapper.sys.SysUserMapper;
import com.tianjin.frontLoaded.service.sys.SysUserService;
import com.tianjin.frontLoaded.util.DateTimeUtils;
import com.tianjin.frontLoaded.util.PoiUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author RainRAY
 * @Date 2022/8/24
 * @Version 1.0
 */
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<String> getUserPermissions(Long userId) {
        return sysUserMapper.getUserPermissions(userId);
    }

    @Override
    public List<String> getUserRole(Long userId) {
        return sysUserMapper.getUserRole(userId);
    }

    @Override
    public List<String> getUserRoleRemarks(Long userId) {
        return sysUserMapper.getUserRoleRemarks(userId);
    }

    @Override
    public File createUserExcelFile(List<SysUser> sysUserList) {
        if (sysUserList == null) {
            sysUserList = new ArrayList<>();
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row0 = sheet.createRow(0);
        int columnIndex = 0;
        row0.createCell(columnIndex).setCellValue("No");
        row0.createCell(++columnIndex).setCellValue("ID");
        row0.createCell(++columnIndex).setCellValue("用户名");
        row0.createCell(++columnIndex).setCellValue("昵称");
        row0.createCell(++columnIndex).setCellValue("邮箱");
        row0.createCell(++columnIndex).setCellValue("手机号");
        row0.createCell(++columnIndex).setCellValue("状态");
        row0.createCell(++columnIndex).setCellValue("头像");
        row0.createCell(++columnIndex).setCellValue("创建人");
        row0.createCell(++columnIndex).setCellValue("创建时间");
        row0.createCell(++columnIndex).setCellValue("最后更新人");
        row0.createCell(++columnIndex).setCellValue("最后更新时间");
        for (int i = 0; i < sysUserList.size(); i++) {
            SysUser user = (SysUser) sysUserList.get(i);
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < columnIndex + 1; j++) {
                row.createCell(j);
            }
            columnIndex = 0;
            row.getCell(columnIndex).setCellValue(i + 1);
            row.getCell(++columnIndex).setCellValue(user.getId());
            row.getCell(++columnIndex).setCellValue(user.getUsername());
            row.getCell(++columnIndex).setCellValue(user.getNickName());
            row.getCell(++columnIndex).setCellValue(user.getEmail());
            row.getCell(++columnIndex).setCellValue(user.getStatus());
            row.getCell(++columnIndex).setCellValue(user.getAvatar());
            row.getCell(++columnIndex).setCellValue(user.getCreateBy());
            row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(user.getCreateTime()));
            row.getCell(++columnIndex).setCellValue(user.getLastUpdateBy());
            row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(user.getLastUpdateTime()));
        }
        return PoiUtils.createExcelFile(workbook, "download_user");
    }

    @Override
    public List<SysRole> findUserRoles(Long userId) {
        return sysUserMapper.findUserRoles(userId);
    }


    @Override
    public String getUsernameById(long userId) {
        return sysUserMapper.getUsernameById(userId);
    }
}
