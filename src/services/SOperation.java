package services;

import DAO.ImpMission;
import DAO.ImpOperation;
import DTO.*;
import Enums.Type;

import java.util.Optional;
import java.util.Scanner;

public class SOperation {
    ImpOperation impOperation= new ImpOperation();
    Scanner sc = new Scanner(System.in);
    Operation operation;

    public void ajouterOperation(){
        operation=new Operation();
        System.out.print("Entrer le type operartion : 1-versement\n2-retrait\n");
        int type=sc.nextInt();
        if(type==1){
            operation.setType(Type.versement);
        }
        else {
            operation.setType(Type.retrait);
        }
        System.out.print("Entrer le Mantant :");
        operation.setMontant(sc.nextDouble());
        System.out.print("Entrer le code du client :");
        Compte cl= new Compte();
        cl.setCode(sc.next());
        operation.setCompte(cl);
        System.out.print("Entrer le code du employe :");
        Employe employe= new Employe();
        employe.setMatricule(sc.next());
        operation.setEmploye(employe);
        Optional<Operation> optoperation = impOperation.ajouter(operation);
        optoperation.ifPresent(compte->{
            System.out.println(String.format("*****   AJOUT D'UNE OPERATION  *****"));
        });
    }

    public void supprierEmploye() {
        operation= new Operation();
        System.out.print("Entrer le code d'operation :");
        operation.setNumero(sc.nextInt());
        Optional<Operation> optionalOp= impOperation.supprimer(operation);
        optionalOp.ifPresent(v -> System.out.println(String.format("*****  OPERATION SUPPRIME  *****")));
    }

    public void chercherbyNum(){
        operation= new Operation();
        System.out.print("Entrer le code d'operation :");
        operation.setNumero(sc.nextInt());
        Optional<Operation> optionalOp= impOperation.chercherbyNum(operation);
        optionalOp.ifPresent(op -> {
            System.out.println(op.getNumero() + " " + op.getCompte().getCode() + " " + op.getEmploye().getMatricule() + " " + op.getType() + " " + op.getDateOperation() + " " + op.getMontant());
        });
    }
}
