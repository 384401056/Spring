package test.country;


import com.blueice.mapper.CountryMapper;
import com.blueice.mapper.SysUserMapper;
import com.blueice.model.Country;
import com.blueice.model.CountryExample;
import com.blueice.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseMapperTest;

import java.util.List;


public class CountryTest extends BaseMapperTest {
    @Test
    public void example() {
        SqlSession sqlSession = getSqlsession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);

            CountryExample example = new CountryExample();

            example.setOrderByClause("id desc, countryname asc");//设置排序
            example.isDistinct();//设置去重

            //创建条件
            CountryExample.Criteria criteria = example.createCriteria();

            criteria.andIdGreaterThanOrEqualTo(1);//id >=1
            criteria.andIdLessThan(4); //id<4

            criteria.andCountrycodeLike("%U%");

            //or的情况
            CountryExample.Criteria or = example.or();

            or.andCountrynameEqualTo("中国");

            List<Country> countryList = mapper.selectByExample(example);
            System.out.println("courytnListSize:" + countryList.size());

        } finally {
            sqlSession.close();
        }
    }

}