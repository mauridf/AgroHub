package com.agrohub.domain.validation;

public class CPFValidator implements jakarta.validation.ConstraintValidator<CPFValido, String> {

    @Override
    public boolean isValid(String cpf, jakarta.validation.ConstraintValidatorContext context) {
        return isValido(cpf);
    }

    public static boolean isValido(String cpf) {
        if (cpf == null || cpf.isBlank()) return false;

        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11) return false;
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0, resto;
            for (int i = 1; i <= 9; i++)
                soma += Integer.parseInt(cpf.substring(i - 1, i)) * (11 - i);
            resto = (soma * 10) % 11;
            if (resto == 10 || resto == 11) resto = 0;
            if (resto != Integer.parseInt(cpf.substring(9, 10))) return false;

            soma = 0;
            for (int i = 1; i <= 10; i++)
                soma += Integer.parseInt(cpf.substring(i - 1, i)) * (12 - i);
            resto = (soma * 10) % 11;
            if (resto == 10 || resto == 11) resto = 0;
            return resto == Integer.parseInt(cpf.substring(10, 11));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
