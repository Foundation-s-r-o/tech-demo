import React, { PropsWithChildren } from 'react'

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

const checkDateType = (value?: string | Date) => {
    return value instanceof Date
        ? value.toLocaleDateString()
        : value
}

export default FndtText
