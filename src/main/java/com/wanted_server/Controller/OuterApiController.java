package com.wanted_server.Controller;

import com.wanted_server.Class.OuterApi.Major;
import com.wanted_server.Class.OuterApi.Region;
import com.wanted_server.Class.OuterApi.School;
import com.wanted_server.Dto.NotRoomTeamInfoPersonalDto;
import com.wanted_server.Repository.MajorRepository;
import com.wanted_server.Repository.RegionRepository;
import com.wanted_server.Repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OuterApiController {

    private final RegionRepository regionRepository;
    private final SchoolRepository schoolRepository;
    private final MajorRepository majorRepository;

    @GetMapping("/region")
    public List<Region> getRegions() {
        return regionRepository.findAll();
    }

    @GetMapping("/school")
    public List<School> getSchools() {
        return schoolRepository.findAll();
    }
    @GetMapping("/major")
    public List<Major> getMajors() {
        return majorRepository.findAll();
    }
}
