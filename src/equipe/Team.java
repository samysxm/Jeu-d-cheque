package equipe;

public enum Team {
    blanche, noir, autre;

    /**
     * @return vrai si l'equipe est blanche sinon faux
     */
    public boolean isWhite(){ return this == blanche; }

    /**
     * @return vrai si l'equipe est noire sinon faux
     */
    public boolean isBlack(){ return this == noir; }
}
