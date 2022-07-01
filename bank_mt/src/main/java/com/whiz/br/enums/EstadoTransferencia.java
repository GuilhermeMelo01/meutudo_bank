package com.whiz.br.enums;

public enum EstadoTransferencia {

        CONCLUIDA(1, "Concluida"),
        PROGRAMADA(2, "Programada"),
        CANCELADA(3, "Cancelado");

        private final Integer cod;
        private final String descricao;

        EstadoTransferencia(Integer cod, String descricao) {
            this.cod = cod;
            this.descricao = descricao;
        }

        public Integer getCod() {
            return cod;
        }

        public String getDescricao() {
            return descricao;
        }

        /* Method: O metodo toEnum() recebe como paramentro um Integer com o codigo do estado e retorna
         o estado da transferencia EXEMPLO -> {CONCLUIDA, CANCELADA,..... } */
        public static EstadoTransferencia toEnum(Integer cod){
            if (cod == null) {
                return null;
            }

            for (EstadoTransferencia estadoTransferencia: EstadoTransferencia.values()){
                if (cod.equals(estadoTransferencia.getCod())){
                    return estadoTransferencia;
                }
            }

            throw new IllegalArgumentException("Value is not invalid! "+ cod);
        }
}
