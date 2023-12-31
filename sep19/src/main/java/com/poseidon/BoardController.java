package com.poseidon;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board")
	public String board() {
		List<Map<String, Object>> list = boardService.boardList(); 
		JSONObject json = new JSONObject();
		JSONArray arr = new JSONArray(list);
		json.put("list", arr);
		System.out.println(json.toString());
		return json.toString();
	}
	
	//2023-09-20
	@PostMapping("/write")
	public String write(@RequestBody Map<String, Object> map) {
		//System.out.println(map);
		int result = boardService.write(map);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam(name="bno", required = true) int bno) {
		Map<String, Object> detail = boardService.detail(bno);
		JSONObject json = new JSONObject();
		json.put("detail", detail);
		return json.toString();
	}
	
	//2023-09-21 클라우드 응용 서비스 개발
	@PostMapping("/delete")
	public String delete(@RequestParam(name="bno", required = true) int bno) {
		//System.out.println(bno);
		int result = boardService.delete(bno);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}
	
	@PatchMapping("/update")
	public String update(@RequestBody Map<String, Object> map) {
//		System.out.println(map);
//		{title=0920, content=글 수정합니다., bno=213}
		int result = boardService.update(map);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}
	
	
}
