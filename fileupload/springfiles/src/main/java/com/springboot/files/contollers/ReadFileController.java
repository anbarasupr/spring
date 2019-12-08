package com.springboot.files.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.files.entities.Account;
import com.springboot.files.service.ReadFileService;

@Controller
@CrossOrigin

public class ReadFileController {

	@Autowired
	private ReadFileService readFileService;

	@GetMapping("/test/{name}")
	public String test(@PathVariable String name) {
		return readFileService.test(name);
	}

	@GetMapping({ "/", "findAll" })
	public String home(Model model) {
		model.addAttribute("account", new Account());
		List<Account> accounts = readFileService.findAll();
		model.addAttribute("accounts", accounts);
		return "view/accounts";
	}

	@PostMapping("/fileupload")
	public String uploadFile(@ModelAttribute Account account, RedirectAttributes redirectAttributes) {
		boolean flag = readFileService.saveDataFromUploadFile(account.getFile());
		if (flag) {
			redirectAttributes.addAttribute("successMessage", "File Uploaded Successfully");
		} else {
			redirectAttributes.addAttribute("failureMessage", "File Upload Failed. Please try again...");
		}
		return "redirect:/";
	}

}
