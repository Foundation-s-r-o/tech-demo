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
    const textValue = value
        ? value instanceof Date
            ? value.toLocaleDateString()
            : value
        : children

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
