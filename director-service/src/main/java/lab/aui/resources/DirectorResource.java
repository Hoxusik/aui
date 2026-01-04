package lab.aui.resources;

import lab.aui.entities.DirectorDTO;
import lab.aui.entities.DirectorUpdateDTO;
import lab.aui.entities.Director;
import lab.aui.services.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/directors")
@RequiredArgsConstructor
public class DirectorResource {
    @Autowired
    private DirectorService directorService;

    @GetMapping("") // zeby wyswietlic zbior
    public List<DirectorDTO> getAllDirectors() {
        return directorService.findAll().stream()
                .map(DirectorDTO::from)
                .sorted()
                .toList();
    }

    @GetMapping("/{id}") //zeby wyswietlic jednego konkretnego
    public ResponseEntity<DirectorDTO> getDirectorById(@PathVariable UUID id) {
        return directorService.findById(id)
                .map(d -> ResponseEntity.ok(DirectorDTO.from(d)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("") //jak robie create
    public ResponseEntity<DirectorDTO> addDirector(@RequestBody DirectorUpdateDTO dto) {
        Director d = directorService.create(dto.getFirstName(), dto.getLastName(), dto.getBirthYear());
        return ResponseEntity
                .created(URI.create("/api/directors/" + d.getId()))
                .body(DirectorDTO.from(d));
    }

    @PutMapping("/{id}") //jak robie update
    public ResponseEntity<DirectorDTO> updateDirector(@PathVariable UUID id, @RequestBody DirectorUpdateDTO dto) {
        var opt = directorService.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        var d = opt.get();
        d.setFirstName(dto.getFirstName());
        d.setLastName(dto.getLastName());
        d.setBirthYear(dto.getBirthYear());
        d = directorService.save(d);

        return ResponseEntity.ok(DirectorDTO.from(d));
    }

    @DeleteMapping("/{id}") //jak robie delete
    public ResponseEntity<Void> deleteDirector(@PathVariable UUID id) {
        if (!directorService.existsById(id)) return ResponseEntity.notFound().build();
        directorService.deleteById(id); // cascade + orphanRemoval usunie powiązane filmy
        return ResponseEntity.noContent().build();
    }
}