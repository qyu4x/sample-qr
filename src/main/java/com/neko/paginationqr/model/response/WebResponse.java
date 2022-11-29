package com.neko.paginationqr.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebResponse <T>{

    private Integer countPage;

    private T data;
}
