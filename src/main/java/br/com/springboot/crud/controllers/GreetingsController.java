package br.com.springboot.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.crud.model.Usuario;
import br.com.springboot.crud.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired /*IC/CD ou CDI - Injeção de dependencia*/
	private UsuarioRepository usuarioRepository;
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(name);
    	usuarioRepository.save(usuario);
        return "Hello " + name + "!";
    }
    
    @GetMapping(value = "listartodos")
    @ResponseBody /*RETORNAR OS DADOS PARA O CORPO DA RESPOSTA*/
    public ResponseEntity<List<Usuario>> listarUsuario() {
    	
    	List<Usuario> usuarios = usuarioRepository.findAll(); /*EXECULTA A CONSULTA NO BANCO DE DADOS*/
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/*RETORNAR A LISTA EM JSON*/
    }
    
    @PostMapping(value = "salvar") /*MAPEIA A URL*/
    @ResponseBody /*RETORNAR OS DADOS PARA O CORPO DA RESPOSTA*/
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { /*@REQUESTBODY: RECEBE OS DADOS PARA SALVAR*/
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    
}
