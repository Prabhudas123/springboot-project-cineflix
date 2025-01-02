package com.cineflix.cineAPI.dto;

import java.util.List;

public record MoviePageResponse(List<CinemaDto> cinemaDtos,
                                Integer pageNum,
                                Integer pageSize,
                                int totalElements,
                                long totalPages,
                                boolean isLast) {
}
