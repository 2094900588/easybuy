package com.easybuy.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EasybuyProductCategory {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer parentid;
    private Integer type;
    private Integer iconclass;
}
