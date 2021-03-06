package br.edu.ifsp.spo.bulls.common.api.enums;

public enum CodeException {

    AU001("AU001: Author not found", "AU001"),
    AU002("AU002: Author already exists", "AU002"),
    AT001("AT001: Error, wrong password or user not found", "AT001"),
    AT002("AT002: Error, token not found", "AT002"),
    AT003("AT003: Password already changed", "AT003"),
    US001("US001: User not found", "US001"),
    US002("US002: Email already used", "US002"),
    US003("US003: Password can't be null", "US003"),
    US004("US004: Token not found", "US004"),
    US005("US005: Username already used", "US005"),
    BK001("BK001: ISBN already exists", "BK001"),
    BK002("BK002: Book not found", "BK002"),
    BK003("BK003: Book should have at least 1 author", "BK003"),
    RT001("RT001: Reading Tracking not found", "RT001"),
    RT002("RT002: Page number greater than the total pages of the book", "RT002"),
    RT003("RT003: Book already completed", "RT002"),
    RT004("RT004: Conflict IDs", "RTO04"),
    RT005("RT005: Page smaller than last tracking", "RT005"),
    RT006("RT006: Page can´t be zero", "RT006"),
    TA001("TA001: Last tracking is not finished yeat", "TA001"),
    TA002("TA002: TrackingGroup not found", "TA002"),
    TG001("TG001: Tag not found", "TG001"),
    TG002("TG002: Tag sent does not match ID", "TG002"),
    PF001("PF001: Profile not found", "PF001"),
    UB001("UB001: UserBook not found", "UB001"),
    UB002("UB002: Invalid status", "UB002"),
    UB003("UB003: Userbook already in bookcase", "UB003"),
    UB004("UB004: Userbook sent does not match ID", "UB004"),
    TK001("TK001: Invalid token", "TK001"),
    FR001("FR001: Request not found", "FR001"),
    FR002("FR002: Conflict while removing friend", "FR002"),
    BR001("BR001: Book recommendation not found", "BR001"),
    RE001("RE001: Review not found", "RE001"),
    RE002("RE002: Review sent does not match ID", "RE002"),
    RTG001("RTG001: Reading target not found", "RTG001"),
    RTG002("RTG002: Reading target already exists", "RTG002"),
    RTG003("RTG003: Userbook not in the list", "RTG003"),
    PT001("PT001: Post not found", "PT001");




    private final String text;
    private final String code;

    CodeException(final String text, String number) {
        this.text = text;
        this.code = number;
    }

    public String getText() {
        return text;
    }

    public String getCode() {
        return code;
    }
}
