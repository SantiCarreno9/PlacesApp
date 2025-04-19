package com.comp304.santiago_dongheun_comp304sec002_lab04.data

import com.comp304.santiago_dongheun_comp304sec002_lab04.R

class PlacesRepositoryImpl : PlacesRepository {
    val places = listOf(
        // Historic Places
        Place(
            name = "Gyeongbokgung Palace",
            latitude = 37.579617,
            longitude = 126.977041,
            address = "161, Sajik-ro, Jongno-gu, Seoul",
            image = R.drawable.gyeongbokgung,
            category = Category.Historic
        ),
        Place(
            name = "Changdeokgung Palace",
            latitude = 37.579389,
            longitude = 126.991033,
            address = "99, Yulgok-ro, Jongno-gu, Seoul",
            image = R.drawable.changdeokkgung,
            category = Category.Historic
        ),
        Place(
            name = "Bukchon Hanok Village",
            latitude = 37.582778,
            longitude = 126.983333,
            address = "37, Gyedong-gil, Jongno-gu, Seoul",
            image = R.drawable.bukchon,
            category = Category.Historic
        ),
        Place(
            name = "Deoksugung Palace",
            latitude = 37.565833,
            longitude = 126.975278,
            address = "99, Sejong-daero, Jung-gu, Seoul",
            image = R.drawable.deoksugung,
            category = Category.Historic
        ),

        // Parks
        Place(
            name = "Namsan Park",
            latitude = 37.551245,
            longitude = 126.988216,
            address = "105, Namsangongwon-gil, Jung-gu, Seoul",
            image = R.drawable.namsan,
            category = Category.Park
        ),
        Place(
            name = "Hangang Park",
            latitude = 37.517154,
            longitude = 126.995127,
            address = "330, Yeouidong-ro, Yeongdeungpo-gu, Seoul",
            image = R.drawable.hangang,
            category = Category.Park
        ),
        Place(
            name = "Olympic Park",
            latitude = 37.521897,
            longitude = 127.121649,
            address = "424, Olympic-ro, Songpa-gu, Seoul",
            image = R.drawable.olympic,
            category = Category.Park
        ),
        Place(
            name = "Yeouido Park",
            latitude = 37.525795,
            longitude = 126.934671,
            address = "68, Yeouido-dong, Yeongdeungpo-gu, Seoul",
            image = R.drawable.yeouido,
            category = Category.Park
        ),

        // Museums
        Place(
            name = "National Museum of Korea",
            latitude = 37.524067,
            longitude = 126.980515,
            address = "137, Seobinggo-ro, Yongsan-gu, Seoul",
            image = R.drawable.nationalmuseum,
            category = Category.Museum
        ),
        Place(
            name = "War Memorial of Korea",
            latitude = 37.537881,
            longitude = 126.977020,
            address = "29, Itaewon-ro, Yongsan-gu, Seoul",
            image = R.drawable.warmemeorial,
            category = Category.Museum
        ),
        Place(
            name = "National Folk Museum",
            latitude = 37.579389,
            longitude = 126.977041,
            address = "37, Samcheong-ro, Jongno-gu, Seoul",
            image = R.drawable.folkmuseum,
            category = Category.Museum
        ),

        // Shopping
        Place(
            name = "Myeongdong Shopping Street",
            latitude = 37.563826,
            longitude = 126.982830,
            address = "Myeongdong-gil, Jung-gu, Seoul",
            image = R.drawable.myeongdong,
            category = Category.Shopping
        ),
        Place(
            name = "COEX Mall",
            latitude = 37.508844,
            longitude = 127.059288,
            address = "513, Yeongdong-daero, Gangnam-gu, Seoul",
            image = R.drawable.coex,
            category = Category.Shopping
        ),
        Place(
            name = "Times Square",
            latitude = 37.517305,
            longitude = 126.903645,
            address = "15, Yeongjung-ro, Yeongdeungpo-gu, Seoul",
            image = R.drawable.timessquare,
            category = Category.Shopping
        )
    )

    override fun getPlacesByCategory(category: Category): List<Place> {
        return places.filter { it.category == category }
    }

    override fun getPlace(index: Int): Place {
        val placeIndex = if (index > places.size - 1) 0
        else index
        return places[placeIndex]
    }

    override fun getPlaceIndex(place: Place): Int {
        return places.indexOf(place)
    }
}