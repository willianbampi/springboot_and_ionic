package com.cursomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.dto.EmailDTO;
import com.cursomc.security.UserSpringSecurity;
import com.cursomc.security.utils.JWTUtil;
import com.cursomc.services.AuthorizationService;
import com.cursomc.services.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/auth")
public class AuthorizationResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@ApiOperation(value="Realiza o refresh do token")
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response){
		UserSpringSecurity userSpringSecurity = UserService.authenticated();
		String token = jwtUtil.generateToken(userSpringSecurity.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Envia email com nova senha")
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgotPassword(@Valid@RequestBody EmailDTO emailDTO){
		authorizationService.sendNewPassword(emailDTO.getEmail());
		return ResponseEntity.noContent().build();
	}

}