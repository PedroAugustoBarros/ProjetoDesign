package br.com.pedro.projetodesign.util;

import android.util.Log;

import java.util.ArrayList;


public class UsuarioSingleton {


    private static Usuario usuario;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        UsuarioSingleton.usuario = usuario;
    }

    public static class Usuario {

        private int id;
        private String login;
        private String cidade;
        private String uf;
        private String celular;
        private int tipo_tabela;
        private String multiplicador;
        private String versaoApp;
        private String token;

        private String id_banca;
        private String nome_banca;
        private int id_usuario;
        private String nome_usuario;
        private String margem_odds;
        private String nivel;
        private String timeStamp;

        private int modulo_bolao;
        private int modulo_ufc;

        private String id_liga;
        private int atualizarLiga;

        private int valorButLiga;
        private int diaTabela;

        private int dias_tabela;
        private int direito_autoral;

        private int tipo_lutas;

        private int tipo;

        private int fav_balance;
        private double fav_margem;
        private double aza_margem;

        private int cancelar_aposta;

        private float multiplicador_max;

        private String db_name;
        private String minimo_cotacao;

        private String credito_atual;


        int atualizarCheckboxOdd;

        int impressoraCont;


        public ArrayList<String> listaLigaCache = new ArrayList<String>(); // Member


        public ArrayList<String> getListaLigaCache() {
            return listaLigaCache;
        }

        public void setListaLigaCache(ArrayList<String> listaLigaCache) {
            this.listaLigaCache = listaLigaCache;
        }

        public ArrayList<String> setlistaLigaCacheFROMarray(String val) {
            if (val != null && !val.isEmpty()) {
                listaLigaCache.add(val);
            }
            return listaLigaCache;
        }

        public void limparlistaLigaCache() {
            Log.d("Limpou o cache","A listaLigaCache foi limpa ");
            listaLigaCache.clear();
        }

        private String obs ;

        public Usuario() {
            //
        }

        public String getVersaoApp() {
            return versaoApp;
        }

        public void setVersaoApp(String versaoApp) {
            this.versaoApp = versaoApp;
        }

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public String getUf() {
            return uf;
        }

        public void setUf(String uf) {
            this.uf = uf;
        }

        public String getCelular() {
            return celular;
        }

        public void setCelular(String celular) {
            this.celular = celular;
        }

        public String getMultiplicador() {
            return multiplicador;
        }

        public void setMultiplicador(String multiplicador) {
            this.multiplicador = multiplicador;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId_usuario() {
            return id_usuario;
        }

        public Usuario(String versaoApp) {
            this.versaoApp = versaoApp;
        }

        public void setId_usuario(int id_usuario) {
            this.id_usuario = id_usuario;
        }

        public String getNome_usuario() {
            return nome_usuario;
        }

        public void setNome_usuario(String nome_usuario) {
            this.nome_usuario = nome_usuario;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }


        public String getId_banca() {
            return id_banca;
        }

        public void setId_banca(String id_banca) {
            this.id_banca = id_banca;
        }

        public String getNome_banca() {
            return nome_banca;
        }

        public void setNome_banca(String nome_banca) {
            this.nome_banca = nome_banca;
        }

        public String getMargem_odds() {
            return margem_odds;
        }

        public void setMargem_odds(String margem_odds) {
            this.margem_odds = margem_odds;
        }

        public int getTipo_tabela() {
            return tipo_tabela;
        }

        public void setTipo_tabela(int tipo_tabela) {
            this.tipo_tabela = tipo_tabela;
        }

        public Usuario(int tipo_tabela) {
            this.tipo_tabela = tipo_tabela;
        }

        public String getObs() {
            return obs;
        }

        public void setObs(String obs) {
            this.obs = obs;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getNivel() {
            return nivel;
        }

        public void setNivel(String nivel) {
            this.nivel = nivel;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }


        public String getId_liga() {
            return id_liga;
        }

        public void setId_liga(String id_liga) {
            this.id_liga = id_liga;
        }

        public int getAtualizarLiga() {
            return atualizarLiga;
        }

        public void setAtualizarLiga(int atualizarLiga) {
            this.atualizarLiga = atualizarLiga;
        }

        public int getValorButLiga() {
            return valorButLiga;
        }

        public void setValorButLiga(int valorButLiga) {
            this.valorButLiga = valorButLiga;
        }

        public int getDiaTabela() {
            return diaTabela;
        }

        public void setDiaTabela(int diaTabela) {
            this.diaTabela = diaTabela;
        }

        public int getModulo_bolao() {
            return modulo_bolao;
        }

        public void setModulo_bolao(int modulo_bolao) {
            this.modulo_bolao = modulo_bolao;
        }

        public int getDias_tabela() {
            return dias_tabela;
        }

        public void setDias_tabela(int dias_tabela) {
            this.dias_tabela = dias_tabela;
        }

        public int getDireito_autoral() {
            return direito_autoral;
        }

        public void setDireito_autoral(int direito_autoral) {
            this.direito_autoral = direito_autoral;
        }

        public int getTipo() {
            return tipo;
        }

        public void setTipo(int tipo) {
            this.tipo = tipo;
        }

        public int getTipo_lutas() {
            return tipo_lutas;
        }

        public void setTipo_lutas(int tipo_lutas) {
            this.tipo_lutas = tipo_lutas;
        }

        public int getModulo_ufc() {
            return modulo_ufc;
        }

        public void setModulo_ufc(int modulo_ufc) {
            this.modulo_ufc = modulo_ufc;
        }

        public int getFav_balance() {
            return fav_balance;
        }

        public void setFav_balance(int fav_balance) {
            this.fav_balance = fav_balance;
        }

        public double getFav_margem() {
            return fav_margem;
        }

        public void setFav_margem(double fav_margem) {
            this.fav_margem = fav_margem;
        }

        public double getAza_margem() {
            return aza_margem;
        }

        public void setAza_margem(double aza_margem) {
            this.aza_margem = aza_margem;
        }

        public int getCancelar_aposta() {
            return cancelar_aposta;
        }

        public void setCancelar_aposta(int cancelar_aposta) {
            this.cancelar_aposta = cancelar_aposta;
        }

        public float getMultiplicador_max() {
            return multiplicador_max;
        }

        public void setMultiplicador_max(float multiplicador_max) {
            this.multiplicador_max = multiplicador_max;
        }

        public String getDb_name() {
            return db_name;
        }

        public void setDb_name(String db_name) {
            this.db_name = db_name;
        }

        public String getMinimo_cotacao() {
            return minimo_cotacao;
        }

        public void setMinimo_cotacao(String minimo_cotacao) {
            this.minimo_cotacao = minimo_cotacao;
        }

        public int getAtualizarCheckboxOdd() {
            return atualizarCheckboxOdd;
        }

        public void setAtualizarCheckboxOdd(int atualizarCheckboxOdd) {
            this.atualizarCheckboxOdd = atualizarCheckboxOdd;
        }

        public int getImpressoraCont() {
            return impressoraCont;
        }

        public void setImpressoraCont(int impressoraCont) {
            this.impressoraCont = impressoraCont;
        }

        public String getCredito_atual() {
            return credito_atual;
        }

        public void setCredito_atual(String credito_atual) {
            this.credito_atual = credito_atual;
        }
    }

}
