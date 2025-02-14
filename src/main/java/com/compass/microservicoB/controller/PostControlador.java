package com.compass.microservicoB.controller;

import com.compass.microservicoB.clients.JsonPlaceHolderService;
import com.compass.microservicoB.dto.PostDTO;
import com.compass.microservicoB.entity.Post;
import com.compass.microservicoB.exception.ExceptionMensagemDeErro;
import com.compass.microservicoB.service.PostServico;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostControlador 
{
    @Autowired
    private PostServico postServico;

    @Autowired
    private JsonPlaceHolderService jsonApiServices;
 
    @Operation(summary = "Cria um post.",
              responses = {@ApiResponse(responseCode = "201",
                                        description = "Post criado!",
              content = @Content(mediaType = "application/json;charset=UTF-8",
              schema = @Schema(implementation = PostDTO.class))),})
    @PostMapping("/criarPost") 
    public ResponseEntity<Post> criarPost(@RequestBody PostDTO postDTO) 
    {
        Post postCriado = postServico.paraPostDTO(postDTO);
        postCriado = postServico.salvar(postCriado);
        return ResponseEntity.status(HttpStatus.CREATED).body(postCriado);
    }

    @Operation(summary = "Busca todos os posts.", 
              responses = {@ApiResponse(responseCode = "200",
                                        description = "Todos os posts foram encontrados!"),
                           @ApiResponse(responseCode = "404",
                                        description = "Nenhum post foi encontrado!",
              content = @Content(mediaType = "application/json;charset=UTF-8",
              schema = @Schema(implementation = PostDTO.class))),})
    @GetMapping("/todosPosts") 
    public ResponseEntity<List<PostDTO>> buscarTodos() 
    {
        List<Post> post = postServico.buscarTodos();
        List<PostDTO> postDTO = post.stream().map(PostDTO:: new).toList();

        return ResponseEntity.ok().body(postDTO);
    }

    @Operation(summary = "Busca um post pelo seu ID.", 
              responses = {@ApiResponse(responseCode = "200",
                                        description = "Post encontrado!",
              content = @Content(mediaType = "application/json;charset=UTF-8",
              schema = @Schema(implementation = PostDTO.class))),})
    @GetMapping("/buscarPost/{id}")
    public ResponseEntity<Post> buscarPostsPorID(@PathVariable String id) 
    {
        Post post = postServico.buscarPostPorID(id);
        return ResponseEntity.ok().body(post);
    }

      @Operation(summary = "Atualiza um post.",
                responses = {@ApiResponse(responseCode = "204",
                                          description = "Atualização realizada!",
                content = @Content(mediaType = "application/json;charset=UTF-8",
                schema = @Schema(implementation = PostDTO.class))),
                             @ApiResponse(responseCode = "404",
                                          description = "Post não encontrado!",
                content = @Content(mediaType = "application/json;charset=UTF-8",
                schema = @Schema(implementation = ExceptionMensagemDeErro.class))),})
    @PutMapping("/atualizarPost/{id}")
    public ResponseEntity<Post> atualizarPost(@PathVariable String id, @RequestBody PostDTO postDTO) 
    {
        Post post = postServico.paraPostDTO(postDTO);
        post.setId(id);
        
        Optional<Post> postAtualizado = postServico.atualizarPost(id, post);
        
        return postAtualizado.map(p -> ResponseEntity.ok().body(p)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Deleta um post.",
              responses = {@ApiResponse(responseCode = "204",
                                        description = "Post deletado!",
              content = @Content(mediaType = "application/json;charset=UTF-8",
              schema = @Schema(implementation = PostDTO.class))),
                           @ApiResponse(responseCode = "404",
                                        description = "Post não encontrado!",
              content = @Content(mediaType = "application/json;charset=UTF-8",
              schema = @Schema(implementation = ExceptionMensagemDeErro.class))),})
    @DeleteMapping("/deletarPost/{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable String id) 
    {
        postServico.deletarPost(id); 
        return ResponseEntity.noContent().build();
    }    

    @Operation(summary = "Atualiza os dados do banco de dados.",
              responses = {@ApiResponse(responseCode = "201",
                                        description = "Os dados foram atualizados!",
              content = @Content(mediaType = "application/json;charset=UTF-8",
              schema = @Schema(implementation = PostDTO.class))),
                           @ApiResponse(responseCode = "404",
                                        description = "Posts não encontrados no banco!",
              content = @Content(mediaType = "application/json;charset=UTF-8",
              schema = @Schema(implementation = PostDTO.class))),})
    @PostMapping("/buscarDados")
    public ResponseEntity<Void> buscarDados()
    {
        jsonApiServices.buscarDados();
        return ResponseEntity.ok().build();
    }
}