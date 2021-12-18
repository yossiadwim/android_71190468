package id.ac.ukdw.fti.datafilmcrud

class MovieClass{
    var title:String? = null
    var genre: String? = null
    var year:String? = null
    var language:String? = null
    var rating: String? = null
    var sinopsis:String? = null

    constructor(){}

    constructor(genre:String?,lang:String?,rating:String?,sinopsis:String?,title:String?,year:String?){
        this.genre = genre
        this.language = lang
        this.rating = rating
        this.sinopsis = sinopsis
        this.title = title
        this.year = year

    }



}