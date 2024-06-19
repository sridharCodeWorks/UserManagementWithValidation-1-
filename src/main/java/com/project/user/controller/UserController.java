package com.project.user.controller;

import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.user.dto.UserDto;
import com.project.user.dto.UserLogInDto;
import com.project.user.entity.User;
import com.project.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping({ "", "/" })
	public String indexPage(HttpServletRequest httpServletRequest, HttpSession httpSession) {
		clearPageHistory(httpSession);
		return "behaviour/index";
	}

	@GetMapping("/registerPage")
	public String directToRegisterPage(HttpServletRequest httpServletRequest, HttpSession httpSession, Model model,
			UserDto userDto) {

		// @RequestHeader(value = "referer", required = false) String referer
		// model.addAttribute("referer", referer);

		addPageAddressToHistory(httpServletRequest, httpSession);
		model.addAttribute("history", getPageHistory(httpSession));
		model.addAttribute("userDto", userDto);
		return "behaviour/createNewUser";
	}

	@PostMapping("/registerPage")
	public String registerNewUser(HttpServletRequest httpServletRequest, HttpSession httpSession,
			@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, User user, Model model) {

		// @RequestHeader(value = "referer", required = false) String referer
		// model.addAttribute("referer", referer);

		addPageAddressToHistory(httpServletRequest, httpSession);
		model.addAttribute("history", getPageHistory(httpSession));
		if (bindingResult.hasErrors()) {
			return "behaviour/createNewUser";
		} else {
			userService.registerNewUser(userDto, user);
			return "redirect:/user/loginPage";
		}
	}

	@GetMapping("/loginPage")
	public String directToLoginUser(HttpServletRequest httpServletRequest, HttpSession httpSession, Model model,
			UserLogInDto userLogInDto) {

		// @RequestHeader(value = "referer", required = false) String referer
		// model.addAttribute("referer", referer);

		addPageAddressToHistory(httpServletRequest, httpSession);
		model.addAttribute("history", getPageHistory(httpSession));
		model.addAttribute("userLogInDto", userLogInDto);
		return "behaviour/loginUserPage";
	}

	@PostMapping("/loginPage")
	public String loginUser(HttpServletRequest httpServletRequest, HttpSession httpSession, Model model,
			@Valid @ModelAttribute UserLogInDto userLogInDto, BindingResult bindingResult) {

		// @RequestHeader(value = "referer", required = false) String referer
		// model.addAttribute("referer", referer);

		addPageAddressToHistory(httpServletRequest, httpSession);
		model.addAttribute("history", getPageHistory(httpSession));
		Optional<User> user = userService.findByUserEmail(userLogInDto.getUserEmail());
		if (user.isPresent() && !user.get().getUserPassword().equals(userLogInDto.getUserPassword())) {
			bindingResult.addError(new FieldError("userLogInDto", "userPassword", "Incorrect Password"));
		}
		if (bindingResult.hasErrors()) {
			return "behaviour/loginUserPage";
		} else {
			return "behaviour/dashboard";
		}
	}

	public void addPageAddressToHistory(HttpServletRequest httpServletRequest, HttpSession httpSession) {
		String currentUrl = httpServletRequest.getRequestURI().toString();
		LinkedList<String> history = (LinkedList<String>) httpSession.getAttribute("history");
		System.out.println("current history :" + history);
		if (history == null) {
			System.out.println("history is null");
			history = new LinkedList<>();
			httpSession.setAttribute("history", history);
		}

		// to avoid adding same url
		if (history.isEmpty() || !history.contains(currentUrl)) {
			history.add(currentUrl);
			System.out.println("Adding into History:" + currentUrl);
			System.out.println("current history :" + history);
		}
	}

	public LinkedList<String> getPageHistory(HttpSession httpSession) {
		LinkedList<String> history = (LinkedList<String>) httpSession.getAttribute("history");
		System.out.println("pervious pages :" + history);
		return history == null ? new LinkedList<>() : history;
	}

	public void clearPageHistory(HttpSession httpSession) {
		httpSession.setAttribute("history", new LinkedList<>());
		System.out.println("clearing history");
	}
}
