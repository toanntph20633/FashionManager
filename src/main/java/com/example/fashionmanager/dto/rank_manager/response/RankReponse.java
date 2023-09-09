package com.example.fashionmanager.dto.rank_manager.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankReponse {
    private Long id;

    private String rankCode;

    private String rankName;
}
