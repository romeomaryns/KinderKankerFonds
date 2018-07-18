package eu.maryns.romeo.kinderkankerfonds.web.persoon;

import de.balvi.cuba.declarativecontrollers.web.browse.AnnotatableAbstractLookup;
import de.diedavids.cuba.taggable.web.WithTags;

@WithTags(listComponent = "persoonsTable", showTagsInList = true, showTagsAsLink = true, tagLinkOpenType = "NEW_TAB")
public class PersoonBrowse extends AnnotatableAbstractLookup {
}