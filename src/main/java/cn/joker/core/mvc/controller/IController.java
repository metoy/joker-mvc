package cn.joker.core.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.joker.core.mvc.model.Model;
import cn.joker.core.mvc.view.View;

public interface IController {

	public View execute(HttpServletRequest request,HttpServletResponse response,Model model);
}
