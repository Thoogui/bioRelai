<?php
require_once 'dBConnexion.php';

class commandeDAO
{  

	public function getLinesOrder($idCommande)
	{
		$requete = DBConnex::getInstance()->prepare("SELECT * FROM LIGNES_COMMANDE WHERE
		idCommande = :idCommande");
        $requete->execute(array(
            ':idCommande' => $idCommande));
        
        $result = $requete->fetchAll(PDO::FETCH_ASSOC); 
		
		return $result;
	}
	
	public function updateLineOrder($idCommande, $idProduit, $quantite)
	{
		$requete = DBConnex::getInstance()->prepare("UPDATE LIGNES_COMMANDE SET QUANTITELIVREECLIENT = :quantite WHERE idCommande = :idCommande AND idProduit = :idProduit");
        $requete->execute(array(
            ':idCommande' => $idCommande,
			':idProduit' => $idProduit,
			':quantite' => $quantite));
	}
	
}


