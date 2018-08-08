package com.algamoney5.curso.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algamoney5.curso.event.RecursoCriadoEvent;

public class EventoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent event) {

		HttpServletResponse response = event.getResponse();
		Long codigo = event.getCodigo();
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/codigo").buildAndExpand(codigo).toUri();
		response.setHeader("Location", uri.toASCIIString());

	}	

}
