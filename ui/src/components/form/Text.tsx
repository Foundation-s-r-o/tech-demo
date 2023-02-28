import React, { PropsWithChildren } from 'react'
import {checkDateType} from "@common/util";

const FndtText = ({
    value,
    children,
    id,
    ...props
}: PropsWithChildren & {
    value?: string | Date
    id?: string
}) => {
    const textValue = value ? checkDateType(value) : children

    return (
        <div
            {...props}
            id={id}
            className="form-value">
            {textValue}
        </div>
    )
}

export default FndtText
