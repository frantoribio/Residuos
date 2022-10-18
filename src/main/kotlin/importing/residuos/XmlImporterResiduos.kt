package importing.residuos

import aliases.Residuos
import dto.ResiduoDto
import exceptions.ImportException
import formats.IXmlImporter
import kotlinx.serialization.decodeFromString
import nl.adaptivity.xmlutil.serialization.XML
import java.io.InputStream

class XmlImporterResiduos(
    private val xml: XML = XML {
        autoPolymorphic = true
        indentString = "  "
    },
) : IXmlImporter<Residuos> {
    override fun import(input: InputStream): Sequence<ResiduoDto> = sequence {
        kotlin.runCatching {
            xml.decodeFromString<List<ResiduoDto>>(input.reader().readText()).forEach { yield(it) }
        }.onFailure {
            throw ImportException("Error al importar los residuos en formato xml")
        }

    }
}