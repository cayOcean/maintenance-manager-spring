package sistema_manutencao_spring.controller;

import java.time.LocalDateTime;
import java.util.Map;

import sistema_manutencao_spring.model.Manutencao;
import sistema_manutencao_spring.repository.ManutencaoRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/manutencoes")
public class ManutencaoController {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    // Listar todas as solicitações
    @GetMapping
    public ModelAndView list() {
        return new ModelAndView(
                "edit",
                Map.of("manutencoes", manutencaoRepository.findAll(Sort.by("dataHoraSolicitacao").descending()))
        );
    }

    // Tela de criação de manutenção
    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("form", Map.of("manutencao", new Manutencao()));
    }

    // Criar manutenção
    @PostMapping("/create")
    public String create(@Valid Manutencao manutencao, BindingResult result) {
        if (result.hasErrors())
            return "form";

        manutencao.setDataHoraSolicitacao(LocalDateTime.now());
        manutencaoRepository.save(manutencao);
        return "redirect:/manutencoes";
    }

    // Tela de edição
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        var manutencao = manutencaoRepository.findById(id);
        if (manutencao.isPresent() && manutencao.get().getDataHoraConclusao() == null) {
            return new ModelAndView("form", Map.of("manutencao", manutencao.get()));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // Editar manutenção
    @PostMapping("/edit/{id}")
    public String edit(@Valid Manutencao manutencao, BindingResult result) {
        if (result.hasErrors())
            return "form";

        manutencaoRepository.save(manutencao);
        return "redirect:/manutencoes";
    }

    // Tela de confirmação de exclusão
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        var manutencao = manutencaoRepository.findById(id);
        if (manutencao.isPresent()) {
            return new ModelAndView("delete", Map.of("manutencao", manutencao.get()));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // Excluir manutenção
    @PostMapping("/delete/{id}")
    public String delete(Manutencao manutencao) {
        manutencaoRepository.delete(manutencao);
        return "redirect:/manutencoes";
    }

    // Finalizar manutenção
    @PostMapping("/finish/{id}")
    public String finish(@PathVariable Long id) {
        var optionalManutencao = manutencaoRepository.findById(id);
        if (optionalManutencao.isPresent() && optionalManutencao.get().getDataHoraConclusao() == null) {
            var manutencao = optionalManutencao.get();
            manutencao.setDataHoraConclusao(LocalDateTime.now());
            manutencaoRepository.save(manutencao);
            return "redirect:/manutencoes";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
