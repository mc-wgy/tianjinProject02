package com.tianjin.frontLoaded.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author RainRAY
 * @Date 2022/8/24
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto<T> {
    private List<T> params;
}
