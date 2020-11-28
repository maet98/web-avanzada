package edu.pucmm.config;

import edu.pucmm.Service.GerenteService;
import edu.pucmm.model.Gerente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class Utils {

    private GerenteService gerenteService;

    public Utils(@Autowired GerenteService gerenteService) {
        this.gerenteService = gerenteService;
    }

    public Gerente getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null) {
            return gerenteService.buscarPorEmail(auth.getName());
        } else {
            return null;
        }
    }

    public boolean isAdminCurrentUser() {
        Gerente gerente = getCurrentUser();
        if(gerente!= null) {
            return getCurrentUser().getRol().equals("ROLE_ADMIN");
        }
        return false;
    }
}
