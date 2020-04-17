package examples.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MainServiceImpl implements MainService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public long execute(ParamInfo paramInfo) {
        Long selectCount = jdbcTemplate.queryForObject(
                "select count(*) from main_tbl where key = ?",
                new Object[] { paramInfo.key },
                Long.class);
        return selectCount;
    }

}
