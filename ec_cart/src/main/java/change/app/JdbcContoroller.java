package change.app;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("messages")
public class JdbcContoroller {
	/*@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Message> getMessage(){
		return jdbcTemplate.query("select text from messages order by id",(rs,i)->{
			Message m = new Message();
			m.setText(rs.getString("text"));
			return m;
		});
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postMessages(@RequestParam ("text") String text) {
		jdbcTemplate.update("insert into messages(text) values (?)",text);
		
		return text;
	}
	*/
}
