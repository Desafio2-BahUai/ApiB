package com.compass.microservicoB.service;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.compass.microservicoB.domain.Usuario;
import com.compass.microservicoB.dto.UserDTO;
import com.compass.microservicoB.repository.UsuarioRepositorio;
import com.compass.microservicoB.service.exception.ObjectNotFoundException;
 
@Service
public class UsuarioServico 
{
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
 
    public List<Usuario> buscarTodos() 
    {
        return usuarioRepositorio.findAll();
    }

    public Usuario findById(String id) {
		Optional<Usuario> obj = usuarioRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public Usuario insert(Usuario obj) {
		return usuarioRepositorio.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		usuarioRepositorio.deleteById(id);
	}

	public Usuario update(Usuario obj) {
		Usuario newObj = findById(obj.getId());
		updateData(newObj, obj);
		return usuarioRepositorio.save(newObj);
	}

	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public Usuario fromDTO(UserDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}