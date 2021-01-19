package subway.repository

import subway.domain.Section
import subway.domain.Station

object SectionRepository {
    private val sections = mutableListOf<Section>()

    fun sections() = sections.toList()

    fun addSection(section: Section) {
        sections.add(section)
    }

    fun existDownwardByName(name: String): Boolean = sections().any { it.downwardStation.name == name }

    fun existUpwardByName(name: String): Boolean = sections().any { it.upwardStation.name == name }

    fun getUpwardStationByDownwardName(name: String): String = sections()
        .filter { it.downwardStation.name == name }
        .map { it -> it.upwardStation.name }
        .toString()

    fun getDownwardStationByUpwardName(name: String): String = sections()
        .filter { it.upwardStation.name == name }
        .map { it -> it.downwardStation.name }
        .toString()

    fun existStationInLine(station: Station) = sections()
                .any{ it.downwardStation.name == station.name || it.upwardStation.name == station.name }

//    fun deleteSectionByName(name: String) = sections.removeIf { it.name == name }
}
