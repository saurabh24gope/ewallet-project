package com.ewallet.commoncode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedPayLoad {

    private Long userId;

    private String userName;

    private String userEmail;


}
