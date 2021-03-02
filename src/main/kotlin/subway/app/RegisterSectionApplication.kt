package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.repository.LineRepository
import subway.repository.SectionRepository
import subway.repository.StationRepository
import subway.view.getDownwardNameOfSectionToRegister
import subway.view.getLineNameOfSectionToRegister
import subway.view.getSectionDistance
import subway.view.getSectionTime
import subway.view.getUpwardNameOfSectionToRegister
import subway.view.infoMessage
import subway.view.succeedRegisterSection

const val DEFAULT_TIME = 3
const val DEFAULT_DISTANCE = 2

fun registerSection() {
    val lineName = getLineNameOfSectionToRegister()
    val line = Line(lineName)

    val upwardStationName = getUpwardNameOfSectionToRegister()
    val downwardStationName = getDownwardNameOfSectionToRegister()
    val distance = getSectionDistance()
    val time = getSectionTime()
    val section = Section(line, Station(upwardStationName), Station(downwardStationName), distance, time)

    require(StationRepository.existsByName(upwardStationName))
    require(StationRepository.existsByName(downwardStationName))
    require(LineRepository.existsByName(lineName))
    require(section.validSectionToRegister())

    additionalSection(section)

    SectionRepository.addSection(section)

    SectionRepository.changeTerminalStation(section)

    infoMessage()
    succeedRegisterSection()
}

fun additionalSection(section: Section) {
    if (section.upExist()) registerAdditionalSection(section)
}

fun registerAdditionalSection(section: Section) {
    val downwardStationName = SectionRepository.findDownwardNameByUpwardName(section.upwardStation.name)
    val downwardStation = StationRepository.findByName(downwardStationName)
    SectionRepository.deleteSection(section.line.name, section.upwardStation.name, downwardStationName)
    val additionalSection = Section(section.line, section.downwardStation, downwardStation, DEFAULT_TIME, DEFAULT_DISTANCE)
    SectionRepository.addSection(additionalSection)
}
