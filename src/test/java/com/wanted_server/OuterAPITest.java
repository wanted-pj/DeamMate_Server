package com.wanted_server;

import com.wanted_server.Class.OuterApi.Major;
import com.wanted_server.Class.OuterApi.Region;
import com.wanted_server.Class.OuterApi.School;
import com.wanted_server.Repository.MajorRepository;
import com.wanted_server.Repository.RegionRepository;
import com.wanted_server.Repository.SchoolRepository;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
public class OuterAPITest {

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    MajorRepository majorRepository;

    @Autowired
    RegionRepository regionRepository;


    @Test
    @Transactional
    @Rollback(value = false)
    public void 학교_데이터_가져와서_데이터에추가() throws Exception {
        // given
        JSONParser parser = new JSONParser();
        // 경로 읽기
        String path = new ClassPathResource("outerAPI/school.json").getURI().getPath();

        // 가져오기 (json-simple)
        Reader reader = new FileReader(path);
        JSONArray jsonArray = (JSONArray) parser.parse(reader);
        String s = jsonArray.toJSONString();

        // json-simple의 JSONArray -> org.json.JSONArray로 바꿔서 가져옴
        org.json.JSONArray jsonArray1 = new org.json.JSONArray(s);
        ArrayList<School> schools = new ArrayList<>();
        for (int i = 0; i < jsonArray1.length(); i++) {
            JSONObject object = jsonArray1.getJSONObject(i);
            String school = object.getString("school");
            long id = object.getLong("id");
            schools.add(new School(id, school));
        }

        // when
        schoolRepository.saveAll(schools);

        // then
        List<School> all = schoolRepository.findAll();
//        assertArrayEquals(schools.toArray(), all.toArray());
        assertThat(all.size()).as("Check School's Size: %d", all.size())
                .isEqualTo(schools.size());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void 학과_데이터_가져와서_데이터에추가() throws Exception {
        // given
        JSONParser parser = new JSONParser();
        // 경로 읽기
        String path = new ClassPathResource("outerAPI/major.json").getURI().getPath();

        // 가져오기 (json-simple)
        Reader reader = new FileReader(path);
        JSONArray jsonArray = (JSONArray) parser.parse(reader);
        String s = jsonArray.toJSONString();

        // json-simple의 JSONArray -> org.json.JSONArray로 바꿔서 가져옴
        org.json.JSONArray jsonArray1 = new org.json.JSONArray(s);
        ArrayList<Major> majors = new ArrayList<>();
        for (int i = 0; i < jsonArray1.length(); i++) {
            JSONObject object = jsonArray1.getJSONObject(i);
            String major = object.getString("major");
            long id = object.getLong("id");
            majors.add(new Major(id, major));
        }

        // when
        majorRepository.saveAll(majors);

        // then
        List<Major> all = majorRepository.findAll();
//        assertArrayEquals(schools.toArray(), all.toArray());
        assertThat(all.size()).as("Check Major's Size: %d", all.size())
                .isEqualTo(majors.size());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void 지역_데이터_가져와서_데이터에추가() throws Exception {
        // given
        JSONParser parser = new JSONParser();
        // 경로 읽기
        String path = new ClassPathResource("outerAPI/region.json").getURI().getPath();

        // 가져오기 (json-simple)
        Reader reader = new FileReader(path);
        JSONArray jsonArray = (JSONArray) parser.parse(reader);
        String s = jsonArray.toJSONString();

        // json-simple의 JSONArray -> org.json.JSONArray로 바꿔서 가져옴
        org.json.JSONArray jsonArray1 = new org.json.JSONArray(s);
        ArrayList<Region> regions = new ArrayList<>();
        for (int i = 0; i < jsonArray1.length(); i++) {
            JSONObject object = jsonArray1.getJSONObject(i);
            String school = object.getString("region");
            long id = object.getLong("id");
            regions.add(new Region(id, school));
        }

        // when
        regionRepository.saveAll(regions);

        // then
        List<Region> all = regionRepository.findAll();
//        assertArrayEquals(schools.toArray(), all.toArray());
        assertThat(all.size()).as("Check Region's Size: %d", all.size())
                .isEqualTo(regions.size());
    }
}
