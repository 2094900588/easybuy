package com.easybuy.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EasybuyOrderDetail {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer orderid;
    private Integer productid;
    private Integer quantity;
    private Float cost;
}
